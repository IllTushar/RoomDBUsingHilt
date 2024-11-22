package com.example.roomdb_usinghilt.RoomDB.Repository

import androidx.lifecycle.LiveData
import com.example.roomdb_usinghilt.RoomDB.Doa.UserDao
import com.example.roomdb_usinghilt.RoomDB.Entities.RoomEntities
import javax.inject.Inject

class UserRepo @Inject constructor(private val userDao: UserDao) {
    //Get All Users
    fun getAllUser(): LiveData<List<RoomEntities>> = userDao.getAllUsers()

    //Insert User
    suspend fun insertSingleUser(user: RoomEntities) = userDao.insertFunction(user)

    //Delete User
    suspend fun deleteUser(user: RoomEntities) = userDao.deleteUser(user)

}