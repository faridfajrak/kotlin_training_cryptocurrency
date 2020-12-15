package com.faridfajrak.kotlin_playground.repository

import androidx.lifecycle.MutableLiveData
import com.faridfajrak.kotlin_playground.features.currency_list.CurrencySearchResult
import com.faridfajrak.kotlin_playground.tools.callGetCurrencyList

class DataRepository(val localCache: LocalCache, val apiService: ApiService) {

    val loading = MutableLiveData<Boolean>()
    val responseCode = MutableLiveData<Int>()
    val message = MutableLiveData<String>()

    fun getCurrencyLists(): CurrencySearchResult {
        loading.postValue(true)
        val data = localCache.loadCurrencies()
        callGetCurrencyList(apiService, onSuccess = {
            localCache.insertCurrencyList(it)
            loading.postValue(false)
            responseCode.postValue(200)
            message.postValue("Success")
        }
            , onError ={ s: String, i: Int ->

                loading.postValue(false)
                responseCode.postValue(i)
                message.postValue(s)

        })

        return CurrencySearchResult(currencies= data, isLoading = loading,responseCode = responseCode, errorMessage = message)
    }
}