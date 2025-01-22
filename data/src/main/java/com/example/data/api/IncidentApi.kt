package com.example.data.api

import com.example.data.entities.IncidentDto
import com.example.data.entities.IncidentList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IncidentApi {

    @GET("/incidents")
    suspend fun fetchIncidentsList(): Response<IncidentList>

    @POST("/incidents/update")
    suspend fun updateIncident(@Body incident: IncidentDto)
}