package com.example.projectx.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectx.domain.useCases.CallInteractor
import kotlinx.coroutines.launch

class MainViewModel(
    private val callInteractor: CallInteractor,
) : ViewModel() {

    private val _state: MutableLiveData<String> = MutableLiveData("idle")
    val state: LiveData<String> get() = _state

    fun send() {
        viewModelScope.launch {
            _state.value = "Processing"
            callInteractor.sendToApi()
                .onFailure { _state.value = "Error" }
                .onSuccess { _state.value = "Success" }
        }
    }
}