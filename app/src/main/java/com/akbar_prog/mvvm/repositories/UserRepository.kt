package com.akbar_prog.mvvm.repositories

import com.akbar_prog.mvvm.database.AppDatabase
import com.akbar_prog.mvvm.database.entity.User
import com.akbar_prog.mvvm.network.ApiService
import com.akbar_prog.mvvm.network.RetrofitClient

class UserRepository(
    private val userdatabase: AppDatabase,
    private val apiService: ApiService
) {
    suspend fun insertUsers(users:List<User>)=userdatabase.dao().addUser(users)

    suspend fun getUsersFromDb() = userdatabase.dao().getAllUsers()

    suspend fun getUsersFromUri() = apiService.getUsers()


}