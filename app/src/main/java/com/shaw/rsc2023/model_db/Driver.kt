package com.shaw.rsc2023.model_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Driver(
    @PrimaryKey val uid: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "id") val id: String?

)
