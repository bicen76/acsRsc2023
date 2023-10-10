package com.shaw.rsc2023.model_db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Driver::class, Route::class], version = 1)
abstract class DriverRouteDatabase : RoomDatabase() {
    abstract fun driverDao(): DriverDao
    abstract fun routeDao(): RouteDao
}