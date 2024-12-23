package com.example.codingassignment.data.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhoneNumberEntity::class], version = 1, exportSchema = false)
abstract class PhoneRoomDatabase : RoomDatabase() {
    abstract fun phoneNumberDao() : PhoneNumberDao
}