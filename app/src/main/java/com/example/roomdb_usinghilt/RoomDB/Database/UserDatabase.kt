package com.example.roomdb_usinghilt.RoomDB.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomdb_usinghilt.RoomDB.Doa.UserDao
import com.example.roomdb_usinghilt.RoomDB.Entities.RoomEntities

@Database(entities = [RoomEntities::class], version = 2, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "user_data_base"
        val db_migration_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE UserTable ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
            }

        }
    }
}