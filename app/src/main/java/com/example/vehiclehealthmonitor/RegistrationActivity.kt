package com.example.vehiclehealthmonitor

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vehiclehealthmonitor.databinding.ActivityRegistrationBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var service: VehicleService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/register/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()

        // Initialize VehicleService
        service = retrofit.create(VehicleService::class.java)

        binding.btnRegister.setOnClickListener {
            registerVehicle()
        }
    }

    private fun registerVehicle() {
        val make = binding.etMake.text.toString()
        val model = binding.etModel.text.toString()
        val year = binding.etYear.text.toString().toInt()
        val vehicleNumber = binding.etVehicleNumber.text.toString()
        val chassisNumber = binding.etChassisNumber.text.toString()
        val engineStatus = binding.spinnerEngineStatus.selectedItem.toString()
        val fuelLevel = binding.spinnerFuelLevel.selectedItem.toString()
        val vehicleCondition = binding.spinnerVehicleCondition.selectedItem.toString()
        val insuranceProvider = binding.etInsuranceProvider.text.toString()
        val insurancePolicyNumber = binding.etInsurancePolicyNumber.text.toString()
        val insuranceStartDate = binding.etInsuranceStartDate.text.toString()
        val insuranceEndDate = binding.etInsuranceEndDate.text.toString()

        service.register(
            Vehicle(
                make,
                model,
                year,
                vehicleNumber,
                chassisNumber,
                engineStatus,
                fuelLevel,
                vehicleCondition,
                insuranceProvider,
                insurancePolicyNumber,
                insuranceStartDate,
                insuranceEndDate
            )
        ).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegistrationActivity, "Registration successful", Toast.LENGTH_SHORT).show()
                    navigateToLogin()
                } else {
                    Toast.makeText(this@RegistrationActivity, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@RegistrationActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
