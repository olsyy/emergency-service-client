package com.example.domain.usecases

import com.example.common.response.ApiResponse
import com.example.domain.entities.Incident
import com.example.domain.repository.ZoneRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetIncidentsByZoneUseCase @Inject constructor(
    private val zoneRepository: ZoneRepository
) {
    suspend operator fun invoke(zoneId: Long): ApiResponse<List<Incident>> {
        return zoneRepository.getIncidentsByZone(zoneId)
    }
}