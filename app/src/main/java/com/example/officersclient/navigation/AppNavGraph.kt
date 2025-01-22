package com.example.officersclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.domain.entities.Zone
import com.example.home.HomeScreen
import com.example.incident.IncidentsScreen
import com.example.zone.ZonesScreen
import com.example.zone_details.ZoneDetailsScreen
import kotlin.reflect.typeOf

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = HomeRoute) {
        composable<HomeRoute> { HomeScreen(navigateToZoneDetails = { zone ->
            navController.navigate(ZoneDetailsRoute(zone))
        }) }
        composable<ZonesRoute> { ZonesScreen(navigateToZoneDetails = { zone ->
            navController.navigate(ZoneDetailsRoute(zone))
        }) }
        composable<IncidentsRoute> { IncidentsScreen() }
        composable<ZoneDetailsRoute>(
            typeMap = mapOf(
                typeOf<Zone>() to ZoneNavType.ZoneType,
            )
        ) {
            val zone = it.toRoute<ZoneDetailsRoute>()
            ZoneDetailsScreen(
                zone = zone.zone,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}