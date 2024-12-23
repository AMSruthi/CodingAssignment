package com.example.codingassignment.data.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.Locale.IsoCountryCode

interface ApiService {
    @GET("Phone/Generate")
    suspend fun getMobileNumbers(
        @Query("CountryCode") countryCode: String = "IN",
        @Query("Quantity") quantity: Int = 1,
        @Header("X-Api-Key") key : String = "68742b10dbc14eae8a19acd6511c2500"
    ) : List<String>
}