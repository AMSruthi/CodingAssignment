package com.example.codingassignment.di

import androidx.room.Room
import com.example.codingassignment.data.api.ApiService
import com.example.codingassignment.data.api.MainRepository
import com.example.codingassignment.data.roomDb.PhoneRoomDatabase
import com.example.codingassignment.ui.main.MainViewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://randommer.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    single {
        Room.databaseBuilder(
            get(),
            PhoneRoomDatabase::class.java,
            "phone_numbers"
        ).build()
    }

    single { get<PhoneRoomDatabase>().phoneNumberDao() }

    single { MainRepository(get(), get()) }

    single { MainViewModel(get()) }
}