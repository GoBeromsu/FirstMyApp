package com.myapplication.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PlanRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allPlan: LiveData<List<Plan>>

    init {
        val wordsDao = PlanDB.getDatabase(application, viewModelScope).planDao()
        repository = PlanRepository(wordsDao)
        allPlan = repository.allPlan
    }

    fun insert(plan: Plan) = viewModelScope.launch(Dispatchers.IO) { repository.insert(plan) }
}