package com.example.data.api

import com.example.data.entities.IncidentList
import com.example.data.entities.ZoneList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ZoneApi {

    @GET("/zones")
    suspend fun fetchZonesList(): Response<ZoneList>

    @GET("/incidents/{zoneId}")
    suspend fun fetchIncidentsByZone(@Path("zoneId") zoneId: Long): Response<IncidentList>
}