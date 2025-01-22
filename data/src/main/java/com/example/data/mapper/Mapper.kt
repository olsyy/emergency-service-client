package com.example.data.mapper


import com.example.common.response.*
import com.example.data.entities.IncidentDto
import com.example.data.entities.ZoneDto
import com.example.domain.entities.Incident
import com.example.domain.entities.Zone

fun IncidentDto.toIncident() = Incident(
    incidentId = incidentId,
    type = type,
    latitude = latitude,
    longitude = longitude,
    description = description,
    status = status,
    zoneId = zoneId
)

fun Incident.toIncidentDto() = IncidentDto(
    incidentId = incidentId,
    type = type,
    latitude = latitude,
    longitude = longitude,
    description = description,
    status = status,
    zoneId = zoneId
)

fun ZoneDto.toZone() = Zone(
    zoneId = zoneId,
    name = name,
    phone = phone,
    level = level,
    minLatitude = minLatitude,
    maxLatitude = maxLatitude,
    minLongitude = minLongitude,
    maxLongitude = maxLongitude
)

fun Zone.toZoneDto() = Zone(
    zoneId = zoneId,
    name = name,
    phone = phone,
    level = level,
    minLatitude = minLatitude,
    maxLatitude = maxLatitude,
    minLongitude = minLongitude,
    maxLongitude = maxLongitude
)

fun <T : Any, R : Any> ApiResponse<T>.map(transform: (T) -> R): ApiResponse<R> {
    return when (this) {
        is Success -> Success(transform(this.data))
        is Error -> Error(this.code, this.message)
        is Exception -> Exception(this.e)
        Empty, Loading -> this
    } as ApiResponse<R>
}

