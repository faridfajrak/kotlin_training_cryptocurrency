package com.faridfajrak.kotlin_playground.repository

import androidx.lifecycle.LiveData
import com.faridfajrak.kotlin_playground.features.currency_list.CurrencyModel
import com.faridfajrak.kotlin_playground.tools.db.CurrencyDatabase
import java.util.concurrent.Executor

class LocalCache(val db : CurrencyDatabase, val ioExecutor : Executor) {

    fun insertCurrencyList(currencylist : List<CurrencyModel>){
        ioExecutor.execute{
            db.currencyDao().insertCurrencies(currencylist)
        }
    }

    fun loadCurrencies() : LiveData<List<CurrencyModel>> = db.currencyDao().loadCurrencies()

}