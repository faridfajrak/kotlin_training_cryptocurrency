package com.faridfajrak.kotlin_playground.features.currency_list

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class CurrencyModel(
    @PrimaryKey
    val id: String,
    val symbol: String,
    val name: String
) {


}