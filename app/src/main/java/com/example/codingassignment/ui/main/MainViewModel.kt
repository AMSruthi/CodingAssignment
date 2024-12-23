package com.example.codingassignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingassignment.data.api.MainRepository
import com.example.codingassignment.data.roomDb.PhoneNumberEntity
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _phoneNumber = MutableLiveData<List<String>>()
    val phoneNumber: LiveData<List<String>> = _phoneNumber

    private val _phoneNumberDetails = MutableLiveData<List<PhoneNumberEntity>>()
    val phoneNumberDetails: LiveData<List<PhoneNumberEntity>> = _phoneNumberDetails

    fun fetchPhoneNumbers() {
        viewModelScope.launch {
            val phoneNumberList = mutableListOf<String>()
            for (i in 1..4) {
                try {
                    val response = repository.getPhoneNumbers()
                    phoneNumberList.add(response.firstOrNull() ?: "")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            _phoneNumber.postValue(phoneNumberList)
        }
    }

    fun savePhoneNumberDetails(phoneNumberDetails : List<PhoneNumberEntity>){
        viewModelScope.launch {
            repository.insertPhoneNumbers(phoneNumberDetails)
        }
    }

    fun getPhoneNumberDetails(){
        viewModelScope.launch {
            val retrievedDetails = repository.getAllPhoneNumber()
            _phoneNumberDetails.postValue(retrievedDetails)
        }
    }

    fun deleteDetails(){
        viewModelScope.launch {
            repository.deleteDetails()
        }
    }
}