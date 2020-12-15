package com.faridfajrak.kotlin_playground.features.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.faridfajrak.kotlin_playground.R
import com.faridfajrak.kotlin_playground.features.currency_list.CurrenciesListView
import com.faridfajrak.kotlin_playground.repository.ApiService
import com.faridfajrak.kotlin_playground.tools.db.CurrencyDatabase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this,CurrenciesListView::class.java))

    }
}