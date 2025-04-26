package com.example.aiwatchdog.model

data class Incident(
    val id: Int,
    val title: String,
    val description: String,
    val severity: Severity,
    val reported_at: String
) 