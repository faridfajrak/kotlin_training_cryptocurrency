package com.faridfajrak.kotlin_playground.features.currency_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faridfajrak.kotlin_playground.generics.BaseViewModel
import com.faridfajrak.kotlin_playground.repository.ApiService
import com.faridfajrak.kotlin_playground.repository.DataRepository

class CurrencyListViewModel(val dataRepository: DataRepository) : BaseViewModel() {

    class Factory(val dataRepository: DataRepository) : ViewModelProvider.Factory
    {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CurrencyListViewModel(dataRepository) as T
        }
    }

    var CurrencySearchResult = dataRepository.getCurrencyLists()


}