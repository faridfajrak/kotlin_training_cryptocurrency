package com.faridfajrak.kotlin_playground.features.currency_list

import androidx.lifecycle.LiveData

data class CurrencySearchResult(
    val currencies: LiveData<List<CurrencyModel>>,
    val isLoading: LiveData<Boolean>?,
    val responseCode : LiveData<Int>?,
    val errorMessage : LiveData<String>?
)