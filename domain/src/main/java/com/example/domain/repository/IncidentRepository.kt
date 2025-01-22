package com.example.domain.repository

import com.example.common.response.ApiResponse
import com.example.domain.entities.Incident

interface IncidentRepository {

    suspend fun getIncidents() : ApiResponse<List<Incident>>
    suspend fun updateIncident(incident: Incident)
}