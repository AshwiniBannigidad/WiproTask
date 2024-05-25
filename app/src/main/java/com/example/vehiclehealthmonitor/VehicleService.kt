package com.example.vehiclehealthmonitor

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface VehicleService {
    @POST("/register")
    fun register(@Body vehicle: Vehicle): Call<Void>

    @POST("/login")
    fun login(@Body request: LoginRequest): Call<Void>
}
