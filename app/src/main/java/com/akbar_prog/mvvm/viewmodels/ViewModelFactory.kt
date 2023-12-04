package com.akbar_prog.mvvm.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.akbar_prog.mvvm.repositories.UserRepository
import com.akbar_prog.mvvm.utills.NetworkHelper
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val userRepository: UserRepository,
    val networkHelper: NetworkHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepository, networkHelper) as T
        }
        throw IllegalArgumentException("Error")
    }
}