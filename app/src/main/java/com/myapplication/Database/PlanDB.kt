package com.myapplication.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Plan::class], version = 1)
abstract class PlanDB : RoomDatabase() {
    abstract fun planDao(): PlanDao

    companion object {
        private var INSTANCE: PlanDB? = null

        fun getInstance(context: Context): PlanDB? {
            if (INSTANCE == null) {
                synchronized(PlanDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PlanDB::class.java, "plan_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }


            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}