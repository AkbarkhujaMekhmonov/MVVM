package com.akbar_prog.mvvm.network

import com.akbar_prog.mvvm.database.entity.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/users")
    suspend fun getUsers():Response<List<User>>
}