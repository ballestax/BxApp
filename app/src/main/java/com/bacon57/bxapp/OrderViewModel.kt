package com.bacon57.bxapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OrderViewModel: ViewModel() {
    private val _state = MutableStateFlow(OrderState())
    val state = _state.asStateFlow()
}

class OrderState