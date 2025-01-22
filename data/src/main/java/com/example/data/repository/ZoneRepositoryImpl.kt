package com.example.data.repository

import android.util.Log
import com.example.common.response.ApiResponse
import com.example.common.response.handleApi
import com.example.data.api.ZoneApi
import com.example.data.mapper.toIncident
import com.example.data.mapper.toZone
import com.example.domain.entities.Incident
import com.example.domain.entities.Zone
import com.example.domain.repository.ZoneRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ZoneRepositoryImpl @Inject constructor(
    private val zoneApi: ZoneApi,
) : ZoneRepository {

    override suspend fun getZones(): ApiResponse<List<Zone>> {
        Log.d("ZoneScreen", "GetZone")
        return handleApi(
            execute = { zoneApi.fetchZonesList() },
            transform = { list -> list.map { it.toZone() } }
        )
    }

    override suspend fun getIncidentsByZone(zoneId: Long): ApiResponse<List<Incident>> {
        return handleApi(
            execute = { zoneApi.fetchIncidentsByZone(zoneId) },
            transform = { list -> list.map { it.toIncident() } }
        )
    }
}