package com.example.domain.repository

import com.example.common.response.ApiResponse
import com.example.domain.entities.Incident
import com.example.domain.entities.Zone

interface ZoneRepository {

    suspend fun getZones(): ApiResponse<List<Zone>>
    suspend fun getIncidentsByZone(zoneId: Long): ApiResponse<List<Incident>>
}