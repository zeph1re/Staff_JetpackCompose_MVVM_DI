package binar.ganda.staff_jetpackcompose_mvvm_di.di

import binar.ganda.staff_jetpackcompose_mvvm_di.service.StaffService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StaffNetwork {

    const val BASE_URL = "https://6254434289f28cf72b5aed04.mockapi.io/"

    private val logging : HttpLoggingInterceptor
        get(){
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }

        }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideStaffApi(retrofit: Retrofit): StaffService =
        retrofit.create(StaffService::class.java)
}