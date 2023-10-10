package com.shaw.rsc2023.model_db

import android.content.Context
import androidx.room.Room

class DbHelper private constructor() {

    fun getAllUserRoutes(id:Int):List<Route>{
        return if(driverRouteDatabase.isOpen){
            driverRouteDatabase.routeDao().getAllUserRoutes(id)
        }else{
            emptyList()
        }
    }

    fun addDrivers(drivers:List<Driver>){
        if(driverRouteDatabase.isOpen){
            driverRouteDatabase.driverDao().insertAll(drivers)
        }
    }

    fun addRoutes(routes:List<Route>){
        if(driverRouteDatabase.isOpen){
            driverRouteDatabase.routeDao().insertAll(routes)
        }
    }

    fun getAllDrivers():List<Driver>{
        return if(driverRouteDatabase.isOpen){
            driverRouteDatabase.driverDao().getAll()
        }else{
            emptyList()
        }
    }

    companion object {

        @Volatile
        private var instance: DbHelper? = null

        @Volatile
        private lateinit var driverRouteDatabase: DriverRouteDatabase

        fun initialize(applicationContext: Context){
                driverRouteDatabase =  Room.databaseBuilder(
                    applicationContext,
                    DriverRouteDatabase::class.java, "DriverRouteDatabase"
                ).build()
        }

        fun getInstance():DbHelper {
            return instance ?: synchronized(this) {
                DbHelper().also { instance = it }
            }

        }

    }

}