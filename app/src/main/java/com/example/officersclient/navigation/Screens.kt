package com.example.officersclient.navigation

import com.example.domain.entities.Zone
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
object ZonesRoute

@Serializable
object IncidentsRoute

@Serializable
data class ZoneDetailsRoute(val zone: Zone)