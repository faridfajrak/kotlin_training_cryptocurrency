package com.faridfajrak.kotlin_playground.tools

import com.faridfajrak.kotlin_playground.BuildConfig
import com.faridfajrak.kotlin_playground.features.currency_list.CurrencyModel
import com.faridfajrak.kotlin_playground.repository.ApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception


fun callGetCurrencyList(
    apiService: ApiService,
    onSuccess: (data: List<CurrencyModel>) -> Unit,
    onError: (error: String, responseCode: Int) -> Unit
) {
    GlobalScope.launch {

        try {
            val response = apiService.getCurrencyListAsync(
                currency = BuildConfig.vs_currency,
                page = BuildConfig.page,
                perPage = BuildConfig.per_page,
                order = BuildConfig.order
            )
            if (response.isSuccessful) {
                response.body().let {
                    if (it != null)
                        onSuccess(it)
                    else
                        onError("NO Data Available", response.code())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            onError("Connection Problem", 500)
        }
    }

}