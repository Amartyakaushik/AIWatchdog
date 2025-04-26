package com.example.aiwatchdog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aiwatchdog.model.Incident
import com.example.aiwatchdog.model.Severity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*

class IncidentViewModel : ViewModel() {
    private val _incidents = MutableLiveData<List<Incident>>()
    val incidents: LiveData<List<Incident>> = _incidents

    private val _filteredIncidents = MutableLiveData<List<Incident>>()
    val filteredIncidents: LiveData<List<Incident>> = _filteredIncidents

    private var currentFilter: Severity? = null

    init {
        loadIncidents()
    }

    fun loadIncidents() {
        // In a real app, this would load from a repository
        _incidents.value = createMockIncidents()
        applyFilter(currentFilter)
    }

    private fun createMockIncidents(): List<Incident> {
        return listOf(
            Incident(
                1,
                "Biased Recommendation Algorithm",
                "Algorithm consistently favored certain demographics...",
                Severity.MEDIUM,
                "2025-03-15T15:30:00Z"
            ),
            Incident(
                2,
                "LLM Hallucination in Critical Info",
                "LLM provided incorrect safety procedure information...",
                Severity.HIGH,
                "2025-04-01T20:00:00Z"
            ),
            Incident(
                3,
                "Minor Data Leak via Chatbot",
                "Chatbot inadvertently exposed non-sensitive user metadata...",
                Severity.LOW,
                "2025-03-20T14:45:00Z"
            )
        )
    }

    fun applyFilter(severity: Severity?) {
        currentFilter = severity
        val currentIncidents = _incidents.value ?: emptyList()
        _filteredIncidents.value = when (severity) {
            null -> currentIncidents
            else -> currentIncidents.filter { it.severity == severity }
        }
    }

    fun addIncident(title: String, description: String, severity: Severity) {
        val currentIncidents = _incidents.value.orEmpty().toMutableList()
        val newId = (currentIncidents.maxOfOrNull { it.id } ?: 0) + 1

        // Use the same format as the mock data
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val timestamp = dateFormat.format(Date())

        val newIncident = Incident(newId, title, description, severity, timestamp)
        currentIncidents.add(newIncident)
        _incidents.value = currentIncidents
        applyFilter(currentFilter)
    }
} 