package com.example.data.entities

import com.example.common.enums.Level
import kotlinx.serialization.Serializable

internal typealias ZoneList = List<ZoneDto>

@Serializable
data class ZoneDto(
    val zoneId: Long,
    val name: String,
    val phone: String,
    val minLatitude: Double,
    val maxLatitude: Double,
    val minLongitude: Double,
    val maxLongitude: Double,
    val level: Level,
)