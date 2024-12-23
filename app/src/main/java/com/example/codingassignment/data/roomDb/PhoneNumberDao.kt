package com.example.codingassignment.data.roomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PhoneNumberDao {
    @Insert
    suspend fun insertPhoneNumbers(phoneNumbers: List<PhoneNumberEntity>)

    @Query("SELECT * FROM phone_numbers")
    suspend fun getAllPhoneNumbers(): List<PhoneNumberEntity>

    @Query("DELETE FROM phone_numbers")
    suspend fun deleteAll()
}