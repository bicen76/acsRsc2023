package com.shaw.rsc2023.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaw.rsc2023.model.DriverRouteDataSet
import com.shaw.rsc2023.model.RouteModel
import com.shaw.rsc2023.repo.RouteDataStoreInteractor

class RouteScreenViewModel(
    private val routeDataStoreInteractor: RouteDataStoreInteractor = RouteDataStoreInteractor()
) : ViewModel() {

    fun getDriverRouteData(id: String, routeList: List<RouteModel>) {
        _driverRouteDataHolder.postValue(DriverRouteDataSet(filterRouteList(id.toInt(), routeList)))
    }

    fun getDriverRouteDataFromDb(id: String) {
        getDriverRouteData(id, routeDataStoreInteractor.getStoredRouteData(id))
    }

    private fun filterRouteList(selectedId: Int, routeList: List<RouteModel>): List<RouteModel> {
         if (routeList.isNotEmpty() && selectedId >= 0) {
            val adjustedSize = (routeList.size-1)
            for (index in 0..adjustedSize) {
                if (selectedId == routeList[index].id) {
                    return listOf(routeList[index])
                }
                for (subIndex in index..adjustedSize) {
                    if ((selectedId > 0 && selectedId % 2 == 0 && routeList[subIndex].type == "R") ||
                        (selectedId > 0 && selectedId % 5 == 0 && routeList[subIndex].type == "C")
                    ) {
                        return listOf(routeList[subIndex])
                    }
                }
                for (searchIndex in (routeList.size - 1)..index) {
                    if (routeList[searchIndex].type == "I") {
                        return listOf(routeList[searchIndex])
                    }
                }
            }
             return emptyList()
        } else {
             return emptyList()
        }
    }

    private val _driverRouteDataHolder = MutableLiveData(DriverRouteDataSet())
    val driverRouteDataHolder get() = _driverRouteDataHolder

}