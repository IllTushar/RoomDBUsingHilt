package com.example.roomdb_usinghilt

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdb_usinghilt.RoomDB.Entities.RoomEntities
import com.example.roomdb_usinghilt.RoomDB.ViewModel.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: viewModel by viewModels() // Use Hilt to inject ViewModel

    /*
    by viewModels()
This is a property delegate provided by the Jetpack lifecycle-viewmodel-ktx library.
It automatically creates or retrieves an existing instance of the ViewModel associated with the Activity or Fragment.
It ensures that the ViewModel is lifecycle-aware and scoped to the lifecycle of the Activity or Fragment.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val table = RoomEntities(name = "Tushar", email = "gtushar697@gmail.com")
        viewModel.addUser(table)
    }
}