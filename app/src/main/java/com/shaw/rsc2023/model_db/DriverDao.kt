package com.shaw.rsc2023.model_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DriverDao {
    @Query("SELECT * FROM driver")
    fun getAll(): List<Driver>

    @Query("SELECT * FROM driver WHERE uid IN (:driverIds)")
    fun loadAllByIds(driverIds: IntArray): List<Driver>

    @Query("SELECT * FROM driver WHERE name LIKE :nameParam AND " +
            "id = :idParam LIMIT 1")
    fun findByNameAndId(nameParam: String, idParam: Int): Driver

    @Insert
    fun insertAll(drivers: List<Driver>)

    @Delete
    fun delete(driver: Driver)

}