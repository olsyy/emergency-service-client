package com.example.zone

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.common.enums.Level
import com.example.core.components.HandleContentState
import com.example.domain.entities.Zone

@Composable
fun ZonesScreen(
    navigateToZoneDetails: (Zone) -> Unit = {},
    viewModel: ZoneViewModel = hiltViewModel(),
) {
    Log.d("ZonesScreen", "ZonesScreen called")
    val zonesState by viewModel.zones.collectAsState()
    LaunchedEffect(Unit) {
        Log.d("ZonesScreen", "LaunchedEffect called")
        viewModel.loadData()
    }
    HandleContentState(zonesState) { ShowZoneList(it, navigateToZoneDetails) }
}

@Composable
private fun ShowZoneList(zones: List<Zone>, navigateToZoneDetails: (Zone) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(zones) { zone ->
            ZoneItem(zone) {
                navigateToZoneDetails(zone)
            }
        }
    }
}


@Composable
private fun ZoneItem(zone: Zone, onZoneClick: (Zone) -> Unit = {}) {
    ElevatedCard(
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = CardDefaults.cardColors(
            containerColor = getColorByLevel(zone.level)
        ),
        onClick = { onZoneClick(zone) }
    ) {
        Text(
            text = zone.name,
            modifier = Modifier.padding(16.dp)
        )
    }
}

private fun getColorByLevel(level: Level) = when (level) {
    Level.LOW -> Color.Green
    Level.MEDIUM -> Color.Yellow
    Level.HIGH -> Color.Red
}