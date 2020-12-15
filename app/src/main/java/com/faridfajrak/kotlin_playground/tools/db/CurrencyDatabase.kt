package com.faridfajrak.kotlin_playground.tools.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.faridfajrak.kotlin_playground.features.currency_list.CurrencyModel

@Database(entities = [CurrencyModel::class], version = 1,exportSchema = false)

abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao() : CurrencyDao

    companion object{
        fun dataBase(context : Context) : CurrencyDatabase = Room.databaseBuilder(context,CurrencyDatabase::class.java,"currency").build()
    }
}