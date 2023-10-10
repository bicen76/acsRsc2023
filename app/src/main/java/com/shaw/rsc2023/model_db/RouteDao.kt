package com.shaw.rsc2023.model_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RouteDao {
    @Query("SELECT * FROM route")
    fun getAll(): List<Route>
    @Query("SELECT * FROM route WHERE id = :idParam")
    fun getAllUserRoutes(idParam: Int): List<Route>
    @Query("SELECT * FROM route WHERE uid IN (:routeIds)")
    fun loadAllByIds(routeIds: IntArray): List<Route>

    @Query("SELECT * FROM route WHERE type LIKE :typeParam AND " +
            "id = :idParam LIMIT 1")
    fun findByTypeAndId(typeParam: String, idParam: Int): Route

    @Query("SELECT * FROM route WHERE name LIKE :nameParam AND " +
            "id = :idParam LIMIT 1")
    fun findByNameAndId(nameParam: String, idParam: Int): Route

    @Query("SELECT * FROM route WHERE name LIKE :nameParam AND type LIKE :typeParam AND " +
            "id = :idParam LIMIT 1")
    fun findByTypeNameAndId(nameParam: String, typeParam: String, idParam: Int): Route

    @Insert
    fun insertAll(routes: List<Route>)

    @Delete
    fun delete(route: Route)
}