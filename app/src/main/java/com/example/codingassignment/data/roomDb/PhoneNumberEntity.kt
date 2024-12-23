package com.example.codingassignment.data.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_numbers")
data class PhoneNumberEntity(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val phoneNumber : String,
    val phoneType : String
)
