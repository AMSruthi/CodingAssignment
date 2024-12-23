package com.example.codingassignment.data.api

import com.example.codingassignment.data.roomDb.PhoneNumberDao
import com.example.codingassignment.data.roomDb.PhoneNumberEntity

class MainRepository(
    private val apiService: ApiService,
    private val phoneNumberDao: PhoneNumberDao
) {

    suspend fun getPhoneNumbers(): List<String> {
        return try {
            val response = apiService.getMobileNumbers()
            response
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun insertPhoneNumbers(phoneNumber: List<PhoneNumberEntity>) {
        phoneNumberDao.insertPhoneNumbers(phoneNumber)
    }

    suspend fun getAllPhoneNumber(): List<PhoneNumberEntity> {
        return phoneNumberDao.getAllPhoneNumbers()
    }

    suspend fun deleteDetails(){
        return phoneNumberDao.deleteAll()
    }
}