package com.faridfajrak.kotlin_playground.features.currency_list

import com.google.gson.annotations.SerializedName

class CurrencyModel(
    @SerializedName("id")  val id : String,
    @SerializedName("symbol")  val symbol : String,
    @SerializedName("name")  val name : String
){



}