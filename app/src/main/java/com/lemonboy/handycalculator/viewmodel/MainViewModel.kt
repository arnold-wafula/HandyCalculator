package com.lemonboy.handycalculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lemonboy.handycalculator.helper.Resource
import com.lemonboy.handycalculator.helper.SingleLiveEvent
import com.lemonboy.handycalculator.model.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//@ViewModelInject deprecated. Now using @Inject
@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepo: MainRepo) : ViewModel() {

    //cached
    private val _data = SingleLiveEvent<Resource<ApiResponse>>()

    //public
    val data = _data
    val convertedRate = MutableLiveData<Double>()


    //Public function to get the result of conversion
    fun getConvertedData(access_key: String, from: String, to: String, amount: Double) {
        viewModelScope.launch {
            mainRepo.getConvertedData(access_key, from, to, amount).collect {
                data.value = it
            }
        }
    }
}