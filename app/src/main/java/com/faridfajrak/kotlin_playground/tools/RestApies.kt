package com.faridfajrak.kotlin_playground.tools

import com.faridfajrak.kotlin_playground.features.currency_list.CurrencyModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET
import java.lang.Exception


fun callGetCurrencyList(
    apiService: ApiService,
    onSuccess: (data: List<CurrencyModel>) -> Unit,
    onError : (error : String, responseCode : Int) -> Unit
) {
    GlobalScope.launch {
        val response = apiService.getCurrencyList().await()
        try {
            if (response.isSuccessful) {
                response.body().let {
                    if (it != null)
                        onSuccess(it)
                    else
                        onError("NO Data Available", response.code())
                }
            }
        }
        catch (e : Exception)
        {
            e.printStackTrace()
            onError("Connection Problem",500)
        }
    }

}