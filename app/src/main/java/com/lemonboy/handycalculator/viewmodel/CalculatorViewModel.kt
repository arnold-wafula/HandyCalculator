package com.lemonboy.handycalculator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lemonboy.handycalculator.db.HistoryDatabase
import com.lemonboy.handycalculator.repository.HistoryRepository
import kotlinx.coroutines.launch

class CalculatorViewModel(private val database: HistoryDatabase, application: Application): AndroidViewModel(application) {
    private val historyRepository = HistoryRepository(database)

    private val _inputString = MutableLiveData<String>()
    val inputString: LiveData<String> get() = _inputString

    private val _outputString = MutableLiveData<String>()
    val outputString: LiveData<String> get() = _outputString

    private var id: Int = 0

    private fun appendCalculation() {
        viewModelScope.launch {
            historyRepository.addCalculation(
                id++,
                _inputString.value.toString(),
                _outputString.value.toString()
            )
        }
    }

}