package com.example.data.entities

import com.example.common.constants.AppConstants.UNDEFINED_ZONE_ID
import com.example.common.enums.Status
import com.example.common.enums.Type
import kotlinx.serialization.Serializable

internal typealias IncidentList = List<IncidentDto>

@Serializable
data class IncidentDto(
    val incidentId: Long? = null,
    val type: Type,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val status: Status,
    val zoneId: Long? = UNDEFINED_ZONE_ID
)