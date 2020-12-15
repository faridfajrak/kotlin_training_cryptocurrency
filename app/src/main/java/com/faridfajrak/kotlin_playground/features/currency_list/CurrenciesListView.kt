package com.faridfajrak.kotlin_playground.features.currency_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.faridfajrak.kotlin_playground.R
import com.faridfajrak.kotlin_playground.databinding.BindingCurrencyListView
import com.faridfajrak.kotlin_playground.generics.BaseActivity
import com.faridfajrak.kotlin_playground.repository.ApiService
import com.faridfajrak.kotlin_playground.repository.DataRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CurrenciesListView : BaseActivity<BindingCurrencyListView,CurrencyListViewModel>() {
    @Inject lateinit var dataRepository: DataRepository


    override fun getClassViewModel(): Class<CurrencyListViewModel> = CurrencyListViewModel::class.java

    override fun getLayoutId(): Int = R.layout.activity_currencies_list

    override fun getViewModelFactory(): ViewModelProvider.Factory = CurrencyListViewModel.Factory(dataRepository)

    override fun initUi() {

        viewModel.CurrencySearchResult.isLoading?.let {
            it.observe(this, Observer {
                if(it)
                    binding.progressBar.visibility = View.VISIBLE
                else
                    binding.progressBar.visibility = View.GONE
            })
        }
        dataRepository.getCurrencyLists().currencies.observe(this, Observer {
            Log.d("Data:",it[0].name)
        })

    }
}