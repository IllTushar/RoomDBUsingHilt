package com.example.roomdb_usinghilt.RoomDB.Doa

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdb_usinghilt.RoomDB.Entities.RoomEntities

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFunction(user: RoomEntities)

    @Query("SELECT * FROM UserTable") // "UserTable" matches @Entity(tableName)
    fun getAllUsers(): LiveData<List<RoomEntities>>

    @Delete
    suspend fun deleteUser(user: RoomEntities)
}