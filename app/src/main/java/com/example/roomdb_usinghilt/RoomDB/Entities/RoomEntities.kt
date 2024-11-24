package com.example.roomdb_usinghilt.RoomDB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTable")
data class RoomEntities(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val isActive: Int,
)