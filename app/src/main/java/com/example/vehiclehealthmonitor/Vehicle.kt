package com.example.vehiclehealthmonitor

import android.os.Parcel
import android.os.Parcelable

data class Vehicle(
    val make: String,
    val model: String,
    val year: Int,
    val vehicleNumber: String,
    val chassisNumber: String,
    val engineStatus: String,
    val fuelLevel: String,
    val vehicleCondition: String,
    val insuranceProvider: String,
    val insurancePolicyNumber: String,
    val insuranceStartDate: String,
    val insuranceEndDate: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(make)
        parcel.writeString(model)
        parcel.writeInt(year)
        parcel.writeString(vehicleNumber)
        parcel.writeString(chassisNumber)
        parcel.writeString(engineStatus)
        parcel.writeString(fuelLevel)
        parcel.writeString(vehicleCondition)
        parcel.writeString(insuranceProvider)
        parcel.writeString(insurancePolicyNumber)
        parcel.writeString(insuranceStartDate)
        parcel.writeString(insuranceEndDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Vehicle> {
        override fun createFromParcel(parcel: Parcel): Vehicle {
            return Vehicle(parcel)
        }

        override fun newArray(size: Int): Array<Vehicle?> {
            return arrayOfNulls(size)
        }
    }
}
