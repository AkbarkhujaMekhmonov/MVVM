package com.akbar_prog.mvvm.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akbar_prog.mvvm.database.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(users:List<User>)

    @Query("select * from users")
    suspend fun getAllUsers(): List<User>


}