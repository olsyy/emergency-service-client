package com.example.zone_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.components.HandleContentState
import com.example.domain.entities.Incident
import com.example.domain.entities.Zone
import com.example.incident_details.IncidentDetailsDialog

@Composable
fun ZoneDetailsScreen(
    zone: Zone,
    viewModel: ZoneDetailsViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = { TopBarDetails(navigateBack) }
    ) { paddingValues ->
        val incidentsState by viewModel.incidents.collectAsState()
        var shouldRefresh by remember { mutableStateOf(false) }
        LaunchedEffect(Unit, shouldRefresh) {
            viewModel.loadData(zone.zoneId)
            shouldRefresh = false
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ZoneInfo(zone)
            HandleContentState(incidentsState) { IncidentsList(it) { shouldRefresh = true } }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarDetails(navigateBack: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Zone Details") },
        navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Composable
private fun ZoneInfo(zone: Zone) {
    ElevatedCard(
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = "Name: ${zone.name}",
            modifier = Modifier
                .padding(8.dp),
        )
        Text(
            text = "Phone: ${zone.phone}",
            modifier = Modifier
                .padding(8.dp),
        )
        Text(
            text = "Level: ${zone.level.name}",
            modifier = Modifier
                .padding(8.dp),
        )
    }
}

@Composable
private fun IncidentsList(incidents: List<Incident>, onRefresh: () -> Unit) {
    var selectedIncident by remember { mutableStateOf<Incident?>(null) }
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(incidents) { incident ->
            IncidentItem(incident) { selectedIncident = it }
        }
    }
    selectedIncident?.let { incident ->
        IncidentDetailsDialog(
            incident = incident,
            onDismissRequest = {
                selectedIncident = null
                onRefresh()
            }
        )
    }
}

@Composable
private fun IncidentItem(incident: Incident, onIncidentClick: (Incident) -> Unit = {}) {
    ElevatedCard(
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        onClick = { onIncidentClick(incident) }
    )
    {
        Text(
            text = incident.description,
            modifier = Modifier.padding(16.dp)
        )
    }
}