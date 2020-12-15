package com.faridfajrak.kotlin_playground.tools.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.faridfajrak.kotlin_playground.features.currency_list.CurrencyModel

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM CurrencyModel")
    fun loadCurrencies() : LiveData<List<CurrencyModel>>

    @Insert(onConflict = REPLACE)
    fun insertCurrencies(currencyList : List<CurrencyModel>)

    @Query("DELETE FROM CurrencyModel")
    fun deleteAll()

}