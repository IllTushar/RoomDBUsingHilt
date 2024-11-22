package com.example.roomdb_usinghilt.RoomDB.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb_usinghilt.RoomDB.Entities.RoomEntities
import com.example.roomdb_usinghilt.RoomDB.Repository.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class viewModel @Inject constructor(private val repo: UserRepo) : ViewModel() {
    val allUsers: LiveData<List<RoomEntities>> = repo.getAllUser()

    fun addUser(user: RoomEntities) {
        viewModelScope.launch {
            repo.insertSingleUser(user)
        }
    }

    fun deleteUser(user: RoomEntities) {
        viewModelScope.launch {
            repo.deleteUser(user)
        }
    }
}