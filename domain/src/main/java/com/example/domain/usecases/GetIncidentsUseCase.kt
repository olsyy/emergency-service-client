package com.example.domain.usecases

import com.example.common.response.ApiResponse
import com.example.domain.entities.Incident
import com.example.domain.repository.IncidentRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetIncidentsUseCase @Inject constructor(
    private val incidentRepository: IncidentRepository
) {
    suspend operator fun invoke(): ApiResponse<List<Incident>> {
        return incidentRepository.getIncidents()
    }
}