package com.example.domain.usecases

import com.example.domain.entities.Incident
import com.example.domain.repository.IncidentRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateIncidentUseCase @Inject constructor(
    private val incidentRepository: IncidentRepository
) {
    suspend operator fun invoke(incident: Incident) {
        return incidentRepository.updateIncident(incident)
    }
}