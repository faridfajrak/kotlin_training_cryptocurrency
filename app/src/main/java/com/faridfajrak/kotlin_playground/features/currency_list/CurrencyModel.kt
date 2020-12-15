package com.faridfajrak.kotlin_playground.features.currency_list

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class CurrencyModel(
    @PrimaryKey
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val current_price: Double,
    val high_24h: Double,
    val low_24h: Double,
    val price_change_24h: Double,
    val price_change_percentage_24h: Double,
    val last_updated: String,
) {


}