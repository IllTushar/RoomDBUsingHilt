package com.example.roomdb_usinghilt

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.roomdb_usinghilt.Adapter.recycler_view_adapter

import com.example.roomdb_usinghilt.RoomDB.Entities.RoomEntities
import com.example.roomdb_usinghilt.RoomDB.ViewModel.viewModel
import com.example.roomdb_usinghilt.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
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
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Get All user
        getAllUser()

        // Add User
        binding.addUser.setOnClickListener {
            bottomSheet()
        }
    }

    private fun getAllUser() {
        viewModel.allUsers.observe(this, Observer { response ->
            getAllData(response)
        })
    }

    private fun getAllData(response: List<RoomEntities>?) {
        if (response!!.isNotEmpty()) {
            val adapter = recycler_view_adapter(this@MainActivity)
            adapter.setData(response)
            binding.showLocalData.adapter = adapter
            adapter.setDeleteUserCallback { user ->
                user.let {
                    viewModel.deleteUser(it)
                    getAllUser()
                }
            }
        } else {
            Toast.makeText(this@MainActivity, "Nothing is store", Toast.LENGTH_LONG).show()
        }

    }

    private fun bottomSheet() {
        val bottomSheet = BottomSheetDialog(this@MainActivity)
        bottomSheet.setContentView(R.layout.bottom_sheet)
        bottomSheet.show()

        val name = bottomSheet.findViewById<EditText>(R.id.userName)
        val email = bottomSheet.findViewById<EditText>(R.id.userEmail)
        val save = bottomSheet.findViewById<Button>(R.id.saveUser)



        save?.setOnClickListener {
            val Name = name?.text.toString().trim()
            val Email = email?.text.toString().trim()

            if (Name.isBlank() || Email.isBlank()) {
                bottomSheet.dismiss()
            } else {
                val table = RoomEntities(name = Name, email = Email)
                viewModel.addUser(table)
                bottomSheet.dismiss()
                getAllUser()
            }

        }

    }
}