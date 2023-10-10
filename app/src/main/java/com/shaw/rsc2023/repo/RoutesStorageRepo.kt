package com.shaw.rsc2023.repo

import com.shaw.rsc2023.model.DriverModel
import com.shaw.rsc2023.model.Result
import com.shaw.rsc2023.model.RouteModel

interface RoutesStorageRepo {
    fun storeDriverData(data: Result)
    fun storeRouteData(data: Result)
    fun getStoredDriverData():List<DriverModel>
    fun getStoredRouteData(id:String):List<RouteModel>
}