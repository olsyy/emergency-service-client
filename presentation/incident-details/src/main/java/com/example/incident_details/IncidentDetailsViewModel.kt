package com.example.incident_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Incident
import com.example.domain.usecases.UpdateIncidentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class IncidentDetailsViewModel @Inject constructor(
    private val updateIncidentUseCase: UpdateIncidentUseCase,
) : ViewModel() {

    fun updateIncident(incident: Incident) = viewModelScope.launch {
        updateIncidentUseCase(incident)
    }
}