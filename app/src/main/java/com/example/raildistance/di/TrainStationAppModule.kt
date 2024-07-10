package com.example.raildistance.di

import com.example.raildistance.data.remote.TrainStationsApi
import com.example.raildistance.domain.repository.TrainStationsRepository
import com.example.raildistance.domain.repository.TrainStationsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrainStationAppModule {

    @Provides
    @Singleton
    fun provideTrainStationsApi(): TrainStationsApi {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .baseUrl("https://koleo.pl/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideTrainStationsRepository(trainStationsApi: TrainStationsApi): TrainStationsRepository {
        return TrainStationsRepositoryImpl(trainStationsApi)
    }
}
