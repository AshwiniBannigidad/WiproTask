package com.example.vehiclehealthmonitor

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vehiclehealthmonitor.databinding.ActivityLoginBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
data class LoginRequest(val vehicleNumber: String)


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var service: VehicleService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/login/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()

        // Initialize VehicleService
        service = retrofit.create(VehicleService::class.java)

        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val vehicleNumber = binding.etVehicleNumber.text.toString()

        service.login(LoginRequest(vehicleNumber)).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Login successful, navigate to dashboard or perform appropriate action
                    Toast.makeText(this@LoginActivity, "Login successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, VehicleHealthDashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Login failed, display error message
                    Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Network or server error occurred
                Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
