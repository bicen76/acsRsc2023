package com.shaw.rsc2023.repo

import android.util.Log
import com.shaw.rsc2023.model.DriverModel
import com.shaw.rsc2023.model.Result
import com.shaw.rsc2023.model.RouteModel

class RouteDataStoreInteractor(private val routesStorageRepository: RoutesStorageRepository = RoutesStorageRepository()) :
    RoutesStorageRepo {

    fun storeData(data: Result){
        storeDriverData(data)
        storeRouteData(data)
    }

    override fun storeDriverData(data: Result) {
        Log.d("RSLogging","storeDriverData - $data")
        routesStorageRepository.storeDriverData(data)
    }

    override fun storeRouteData(data: Result) {
        Log.d("RSLogging","storeRouteData - $data")
        routesStorageRepository.storeRouteData(data)
    }

    override fun getStoredDriverData(): List<DriverModel> {
        return routesStorageRepository.getStoredDriverData()
    }

    override fun getStoredRouteData(id: String): List<RouteModel> {
        return routesStorageRepository.getStoredRouteData(id)
    }

}