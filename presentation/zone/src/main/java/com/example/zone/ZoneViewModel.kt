package com.example.zone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.response.ApiResponse
import com.example.common.response.Empty
import com.example.common.response.Error
import com.example.common.response.Loading
import com.example.domain.entities.Zone
import com.example.domain.usecases.GetZonesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZoneViewModel @Inject constructor(
    private val getZonesUseCase: GetZonesUseCase,
) : ViewModel() {

    private val _zones: MutableStateFlow<ApiResponse<List<Zone>>> =
        MutableStateFlow(Empty)
    val zones: StateFlow<ApiResponse<List<Zone>>> = _zones

    init {
        viewModelScope.launch {
            _zones.value = Loading
        }
    }

    fun loadData() = viewModelScope.launch {
        try {
            _zones.value = getZonesUseCase()
        } catch (e: Exception) {
            _zones.value = Error(message = e.message.toString(), code = 999)
        }
    }
}