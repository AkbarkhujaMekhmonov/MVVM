package com.akbar_prog.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.akbar_prog.mvvm.adapters.RVAdapter
import com.akbar_prog.mvvm.database.AppDatabase
import com.akbar_prog.mvvm.databinding.ActivityMainBinding
import com.akbar_prog.mvvm.network.ApiService
import com.akbar_prog.mvvm.network.RetrofitClient
import com.akbar_prog.mvvm.repositories.UserRepository
import com.akbar_prog.mvvm.utills.NetworkHelper
import com.akbar_prog.mvvm.utills.Status
import com.akbar_prog.mvvm.viewmodels.UserViewModel
import com.akbar_prog.mvvm.viewmodels.ViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    lateinit var adapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userRepository =
            UserRepository(AppDatabase.getInstants(applicationContext), RetrofitClient.apiService)
        val networkHelper = NetworkHelper(this)
        userViewModel = ViewModelProvider(
            this,
            ViewModelFactory(userRepository, networkHelper)
        )[UserViewModel::class.java]

        userViewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Toast.makeText(
                        binding.root.context,
                        "loading",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                Status.ERROR -> {
                    Toast.makeText(binding.root.context, "Error: ${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }
                Status.SUCCESS -> {
                    adapter = RVAdapter(it.data!!)
                    binding.rv.adapter = adapter
                }
            }
        })


    }
}