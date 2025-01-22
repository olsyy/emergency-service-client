package com.example.zone_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.response.ApiResponse
import com.example.common.response.Empty
import com.example.common.response.Loading
import com.example.domain.entities.Incident
import com.example.domain.usecases.GetIncidentsByZoneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZoneDetailsViewModel @Inject constructor(
    private val getIncidentsByZone: GetIncidentsByZoneUseCase,
) : ViewModel() {

    private val _incidents: MutableStateFlow<ApiResponse<List<Incident>>> =
        MutableStateFlow(Empty)
    val incidents: StateFlow<ApiResponse<List<Incident>>> = _incidents

    init {
        viewModelScope.launch {
            _incidents.value = Loading
        }
    }

    fun loadData(zoneId: Long) = viewModelScope.launch {

        _incidents.value = getIncidentsByZone(zoneId)
    }
}