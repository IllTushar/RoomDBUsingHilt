package com.example.roomdb_usinghilt.RoomDB.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdb_usinghilt.RoomDB.Doa.UserDao
import com.example.roomdb_usinghilt.RoomDB.Entities.RoomEntities

@Database(entities = [RoomEntities::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "user_data_base"
    }
}