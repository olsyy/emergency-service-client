package com.example.incident

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.response.ApiResponse
import com.example.common.response.Empty
import com.example.common.response.Loading
import com.example.domain.entities.Incident
import com.example.domain.usecases.GetIncidentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncidentViewModel @Inject constructor(
    private val getIncidentsUseCase: GetIncidentsUseCase,
) : ViewModel() {

    private val _incidents: MutableStateFlow<ApiResponse<List<Incident>>> =
        MutableStateFlow(Empty)
    val incidents: StateFlow<ApiResponse<List<Incident>>> = _incidents

    init {
        _incidents.value = Loading
    }

    fun loadData() = viewModelScope.launch {
        _incidents.value = Loading
        _incidents.value = getIncidentsUseCase()
    }
}