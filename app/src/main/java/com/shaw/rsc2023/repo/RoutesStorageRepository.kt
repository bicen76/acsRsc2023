package com.shaw.rsc2023.repo

import com.shaw.rsc2023.model.DriverModel
import com.shaw.rsc2023.model.Result
import com.shaw.rsc2023.model.RouteModel
import com.shaw.rsc2023.model_db.DbHelper

class RoutesStorageRepository : RoutesStorageRepo {
    override fun storeDriverData(data: Result) {
        data.driverDataList?.let {
            if(it.isNotEmpty()){
                DbHelper.getInstance().addDrivers(it.map { e ->
                    com.shaw.rsc2023.model_db.Driver(
                        uid = null,
                        id = e.id,
                        name = e.name
                    )
                }.toList())
            }

        }
    }

    override fun storeRouteData(data: Result) {
        data.routeDataList?.let {
            if(it.isNotEmpty()){
                DbHelper.getInstance().addRoutes(it.map { r ->
                    com.shaw.rsc2023.model_db.Route(
                        uid = null,
                        id = r.id,
                        type = r.type,
                        name = r.name
                    )
                }.toList())
            }
        }
    }

    override fun getStoredDriverData(): List<DriverModel> {
        return DbHelper.getInstance().getAllDrivers().map { e ->
            com.shaw.rsc2023.model.DriverModel().apply {
                id = e.id
                name = e.name
            }
        }.toList()
    }

    override fun getStoredRouteData(id: String): List<RouteModel> {
        val qid = id.toIntOrNull()
        return qid?.let {
            DbHelper.getInstance().getAllUserRoutes(qid).map { r ->
                com.shaw.rsc2023.model.RouteModel().apply {
                    this.id = r.id
                    this.type = r.type
                    this.name = r.name
                }
            }.toList()
        } ?: emptyList()

    }
}