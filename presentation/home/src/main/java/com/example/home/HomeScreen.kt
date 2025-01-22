package com.example.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.domain.entities.Zone
import com.example.incident.IncidentsScreen
import com.example.zone.ZonesScreen

@Composable
fun HomeScreen(
    navigateToZoneDetails: (Zone) -> Unit = {},
) {
    Log.d("HomeScreen", "HomeScreen called")
    Scaffold { paddingValues ->
        TabsBar(
            navigateToZoneDetails = navigateToZoneDetails,
            paddingValues = paddingValues
        )
    }
}


@Composable
private fun TabsBar(
    navigateToZoneDetails: (Zone) -> Unit,
    paddingValues: PaddingValues,
) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Zones", "Incidents")
    Column(modifier = Modifier.padding(paddingValues)) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> ZonesScreen(navigateToZoneDetails)
            1 -> IncidentsScreen()
        }
    }
}
