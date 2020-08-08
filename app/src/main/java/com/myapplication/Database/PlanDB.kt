package com.myapplication.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Plan::class], version = 2)
abstract class PlanDB : RoomDatabase() {
    abstract fun planDao(): PlanDao

    companion object {
        @Volatile
        private var INSTANCE: PlanDB? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PlanDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlanDB::class.java,
                    "plan_table"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()

                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class PlanDBCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.planDao())
                    }
                }
            }
        }


        fun populateDatabase(planDao: PlanDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            planDao.deleteAll()
            var plan = Plan("30초", 30)
            planDao.insert(plan)

        }
    }
}