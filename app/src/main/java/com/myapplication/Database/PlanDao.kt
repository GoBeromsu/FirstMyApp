package com.myapplication.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface PlanDao {
    @Query("SELECT * FROM plan_table")
    fun getAll():List<Plan>

    @Insert(onConflict = REPLACE)
    fun insert(plan:Plan)

    @Query("DELETE from plan_table")
    fun deleteAll()
}