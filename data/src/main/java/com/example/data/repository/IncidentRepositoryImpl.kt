package com.example.data.repository

import com.example.common.response.ApiResponse
import com.example.common.response.handleApi
import com.example.data.mapper.toIncident
import com.example.data.mapper.toIncidentDto
import com.example.data.api.IncidentApi
import com.example.domain.entities.Incident
import com.example.domain.repository.IncidentRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IncidentRepositoryImpl @Inject constructor(
    private val incidentApi: IncidentApi,
) : IncidentRepository {

    override suspend fun getIncidents(): ApiResponse<List<Incident>> {
        return handleApi(
            execute = { incidentApi.fetchIncidentsList() },
            transform = { list -> list.map { it.toIncident() } }
        )
    }

    override suspend fun updateIncident(incident: Incident) {
        incidentApi.updateIncident(incident.toIncidentDto())
    }
}