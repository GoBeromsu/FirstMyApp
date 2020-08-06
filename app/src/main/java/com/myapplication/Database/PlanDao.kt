package com.myapplication.Database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface PlanDao {
    @Query("SELECT * FROM plan_table")
    fun getAll(): List<Plan>

    @Insert(onConflict = REPLACE)
    fun insert(plan: Plan)

    @Query("DELETE from plan_table")
    fun deleteAll()

    @Update(onConflict = 3)
    fun update(plan: Plan)

    @Delete
    fun delete(plan: Plan)
}