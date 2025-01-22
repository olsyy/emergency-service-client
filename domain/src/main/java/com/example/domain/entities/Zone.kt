package com.example.domain.entities

import com.example.common.enums.Level
import kotlinx.serialization.Serializable


@Serializable
data class Zone (
    val zoneId: Long,
    val name: String,
    val phone: String,
    val minLatitude: Double,
    val maxLatitude: Double,
    val minLongitude: Double,
    val maxLongitude: Double,
    val level: Level,
)