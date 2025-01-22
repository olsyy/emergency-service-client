package com.example.domain.usecases

import com.example.common.response.ApiResponse
import com.example.domain.entities.Zone
import com.example.domain.repository.ZoneRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetZonesUseCase @Inject constructor(
    private val zoneRepository: ZoneRepository
) {
    suspend operator fun invoke(): ApiResponse<List<Zone>> {
        return zoneRepository.getZones()
    }
}