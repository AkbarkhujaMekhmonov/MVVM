package com.akbar_prog.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akbar_prog.mvvm.database.entity.User
import com.akbar_prog.mvvm.repositories.UserRepository
import com.akbar_prog.mvvm.utills.NetworkHelper
import com.akbar_prog.mvvm.utills.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(
    private val repository: UserRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val liveData = MutableLiveData<Resource<List<User>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            liveData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                val remoteUsers = repository.getUsersFromUri()
                if (remoteUsers.isSuccessful) {
                    repository.insertUsers(remoteUsers.body() ?: emptyList())
                    liveData.postValue(Resource.success(remoteUsers.body()))
                } else {
                    liveData.postValue(
                        Resource.error(
                            remoteUsers.errorBody()?.string() ?: "",
                            null

                        )
                    )
                }
            }else{
                liveData.postValue(Resource.success(repository.getUsersFromDb()))
            }
        }

    }

    fun getUsers(): LiveData<Resource<List<User>>> = liveData


}