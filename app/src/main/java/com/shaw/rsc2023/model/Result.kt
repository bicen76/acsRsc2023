package com.shaw.rsc2023.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Result : Serializable {
    @SerializedName("drivers")
    @Expose
    val driverDataList: List<DriverModel>? = arrayListOf()
    @SerializedName("routes")
    @Expose
    val routeDataList: List<RouteModel>? = arrayListOf()
}