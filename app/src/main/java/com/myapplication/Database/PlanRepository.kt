package com.myapplication.Database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class PlanRepository(private val planDao: PlanDao) {
    val allPlan:LiveData<List<Plan>> = planDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(plan: Plan) {
        planDao.insert(plan)
    }

    suspend fun delete(plan: Plan) {
        planDao.delete(plan)
    }
    suspend fun update(plan:Plan){
        planDao.update(plan)
    }
}