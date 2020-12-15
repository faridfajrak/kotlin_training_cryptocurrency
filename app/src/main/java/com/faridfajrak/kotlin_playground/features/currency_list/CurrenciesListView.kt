package com.faridfajrak.kotlin_playground.features.currency_list

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.adapters.SearchViewBindingAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faridfajrak.kotlin_playground.R
import com.faridfajrak.kotlin_playground.databinding.BindingCurrencyListView
import com.faridfajrak.kotlin_playground.generics.BaseActivity
import com.faridfajrak.kotlin_playground.repository.ApiService
import com.faridfajrak.kotlin_playground.repository.DataRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CurrenciesListView : BaseActivity<BindingCurrencyListView, CurrencyListViewModel>() {
    @Inject
    lateinit var dataRepository: DataRepository


    lateinit var adapter: CurrencyListAdapter;
    override fun getClassViewModel(): Class<CurrencyListViewModel> =
        CurrencyListViewModel::class.java

    override fun getLayoutId(): Int = R.layout.activity_currencies_list

    override fun getViewModelFactory(): ViewModelProvider.Factory =
        CurrencyListViewModel.Factory(dataRepository)

    override fun initUi() {


        viewModel.CurrencySearchResult.isLoading?.let {
            it.observe(this, Observer {
                if (it)
                    binding.progressBar.visibility = View.VISIBLE
                else
                    binding.progressBar.visibility = View.GONE
            })
        }
        viewModel.CurrencySearchResult.currencies.observe(this, Observer {
            adapter = CurrencyListAdapter(it, onclick = {
                Toast.makeText(this, "clicked on ${it.id}", Toast.LENGTH_SHORT).show()
            })
            binding.rvCurrencies.adapter = adapter
            binding.rvCurrencies.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        })

        binding.searchView.setOnCloseListener ( object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                adapter.let {
                    adapter.applyFilter("")
                }
                return  false
            }
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("SEARCh", newText!!)
                adapter.let {
                    it.applyFilter(newText)
                }
                return true
            }

        })

    }
}