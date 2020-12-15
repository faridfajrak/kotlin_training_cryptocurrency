package com.faridfajrak.kotlin_playground.tools.hilt

import android.content.Context
import com.faridfajrak.kotlin_playground.repository.ApiService
import com.faridfajrak.kotlin_playground.repository.DataRepository
import com.faridfajrak.kotlin_playground.repository.LocalCache
import com.faridfajrak.kotlin_playground.tools.db.CurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object HiltModule {

    @Singleton
    @Provides
    fun provideApiService() : ApiService {
        return  ApiService.create()
    }

    @Singleton
    @Provides
    fun provideCurrencyDb(@ApplicationContext appContext: Context) : CurrencyDatabase
    {
        return CurrencyDatabase.dataBase(appContext)
    }

    @Singleton
    @Provides
    fun provideLocalCache(db:CurrencyDatabase) : LocalCache{
        return  LocalCache(db,Executors.newSingleThreadExecutor())
    }

    @Singleton
    @Provides
    fun provideDataRepository(localCache: LocalCache,apiService: ApiService) : DataRepository{
        return  DataRepository(localCache = localCache, apiService = apiService )
    }

}