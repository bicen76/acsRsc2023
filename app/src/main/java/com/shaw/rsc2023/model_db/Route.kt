package com.shaw.rsc2023.model_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Route(
    @PrimaryKey val uid: Int?,
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "name") val name: String?,
)