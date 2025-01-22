package com.example.incident

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.components.HandleContentState
import com.example.domain.entities.Incident
import com.example.incident_details.IncidentDetailsDialog

@Composable
fun IncidentsScreen(viewModel: IncidentViewModel = hiltViewModel()) {
    val incidentsState by viewModel.incidents.collectAsState()
    var shouldRefresh by remember { mutableStateOf(false) }
    LaunchedEffect(Unit, shouldRefresh) {
        viewModel.loadData()
        shouldRefresh = false
    }
    HandleContentState(incidentsState) { ShowIncidentList(it) { shouldRefresh = true } }
}

@Composable
private fun ShowIncidentList(incidents: List<Incident>, onRefresh: () -> Unit) {
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
            },
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
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), onClick = { onIncidentClick(incident) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = incident.type.name,
                modifier = Modifier
                    .padding(16.dp),
            )
            Text(
                text = incident.status.name,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}