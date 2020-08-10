package com.myapplication.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface PlanDao {
    @Query("SELECT * FROM plan_table")
    fun getAll(): LiveData<List<Plan>>

    @Insert(onConflict = REPLACE)
    fun insert(plan: Plan)

    @Query("DELETE from plan_table")
     fun deleteAll()

    @Update(onConflict = 3)
    suspend fun update(plan: Plan)

    @Delete
    suspend fun delete(plan: Plan)

    @Update
    suspend fun Update(plan: Plan)

}