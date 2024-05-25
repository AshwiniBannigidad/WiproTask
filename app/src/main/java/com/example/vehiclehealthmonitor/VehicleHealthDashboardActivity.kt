package com.example.vehiclehealthmonitor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vehiclehealthmonitor.databinding.ActivityVehicleHealthDashboardBinding

class VehicleHealthDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVehicleHealthDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicleHealthDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vehicle = intent.getParcelableExtra<Vehicle>("vehicle")
        vehicle?.let { displayVehicleInfo(it) }
    }

    private fun displayVehicleInfo(vehicle: Vehicle) {
        binding.tvMake.text = "Make: ${vehicle.make}"
        binding.tvModel.text = "Model: ${vehicle.model}"
        binding.tvYear.text = "Year: ${vehicle.year}"
        binding.tvVehicleNumber.text = "Vehicle Number: ${vehicle.vehicleNumber}"
        binding.tvChassisNumber.text = "Chassis Number: ${vehicle.chassisNumber}"
        binding.tvEngineStatus.text = "Engine Status: ${vehicle.engineStatus}"
        binding.tvFuelLevel.text = "Fuel Level: ${vehicle.fuelLevel}"
        binding.tvVehicleCondition.text = "Vehicle Condition: ${vehicle.vehicleCondition}"
        binding.tvInsuranceProvider.text = "Insurance Provider: ${vehicle.insuranceProvider}"
        binding.tvInsurancePolicyNumber.text = "Insurance Policy Number: ${vehicle.insurancePolicyNumber}"
        binding.tvInsuranceStartDate.text = "Insurance Start Date: ${vehicle.insuranceStartDate}"
        binding.tvInsuranceEndDate.text = "Insurance End Date: ${vehicle.insuranceEndDate}"
    }
}
