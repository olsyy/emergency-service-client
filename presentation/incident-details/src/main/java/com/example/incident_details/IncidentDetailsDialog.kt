package com.example.incident_details


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.common.enums.Status
import com.example.domain.entities.Incident
import com.example.officersclient.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun IncidentDetailsDialog(
    incident: Incident,
    onDismissRequest: () -> Unit,
    viewModel: IncidentDetailsViewModel = hiltViewModel(),
) {
    Dialog(onDismissRequest = onDismissRequest) {
        IncidentDetailsCard(incident, onDismissRequest, viewModel)
    }
}

@Composable
fun IncidentDetailsCard(
    incident: Incident,
    onDismiss: () -> Unit,
    viewModel: IncidentDetailsViewModel,
) {
    var isDropDownExpanded = remember { mutableStateOf(false) }
    var currentStatus = remember { mutableStateOf(incident.status.name) }

    val originalStatus = incident.status.name
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(375.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = incident.description)
            Text(text = "Type: ${incident.type.name}")
            Text(text = "${incident.latitude}, ${incident.longitude}")
            DropdownMenu(
                isDropDownExpanded,
                currentStatus,
                originalStatus,
                onDismiss,
                incident,
                viewModel
            )
        }
    }
}

@Composable
fun DropdownMenu(
    isDropDownExpanded: MutableState<Boolean>,
    currentStatus: MutableState<String>,
    originalStatus: String,
    onDismiss: () -> Unit,
    incident: Incident,
    viewModel: IncidentDetailsViewModel,
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    isDropDownExpanded.value = !isDropDownExpanded.value
                }
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val list = Status.entries.map { it.name }
            Text(text = "Status: ${currentStatus.value}")
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                contentDescription = "DropDown Icon"
            )
            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = { isDropDownExpanded.value = false }
            ) {
                list.forEachIndexed { index, status ->
                    DropdownMenuItem(
                        text = { Text(text = status) },
                        onClick = {
                            isDropDownExpanded.value = false
                            currentStatus.value = status
                        })
                }
            }
        }
        Button(
            onClick = {
                if (currentStatus.value != originalStatus) {
                    CoroutineScope(Dispatchers.IO).launch {
                        withContext(Dispatchers.Main) {
                            viewModel.updateIncident(
                                incident.copy(
                                    status = Status.valueOf(
                                        currentStatus.value
                                    )
                                )
                            )
                        }
                        delay(1000)
                        onDismiss()
                    }
                } else {
                    onDismiss()
                }
            }
        ) {
            Text(text = "OK")
        }
    }
}