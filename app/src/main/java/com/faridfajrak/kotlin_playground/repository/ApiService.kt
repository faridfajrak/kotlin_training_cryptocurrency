package com.faridfajrak.kotlin_playground.repository

import com.faridfajrak.kotlin_playground.BuildConfig
import com.faridfajrak.kotlin_playground.features.currency_list.CurrencyModel
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=50&page=1")
    suspend fun getCurrencyListAsync(@Query("vs_currency") currency : String,@Query("order") order : String,
                                     @Query("per_page") perPage : String,@Query("page") page : String
                                     ) : Response <List<CurrencyModel>>

    companion object{
        fun create() : ApiService {
            val baseUrl = BuildConfig.base_url
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}

