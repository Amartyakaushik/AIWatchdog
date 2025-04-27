package com.example.aiwatchdog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aiwatchdog.R
import com.example.aiwatchdog.model.Incident
import com.example.aiwatchdog.model.Severity
import com.google.android.material.chip.Chip
import java.text.SimpleDateFormat
import java.util.*

class IncidentAdapter(
    private val onItemClick: (Incident) -> Unit
) : ListAdapter<Incident, IncidentAdapter.IncidentViewHolder>(IncidentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_incident, parent, false)
        return IncidentViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.alpha = 0f
        holder.itemView.translationY = 50f
        holder.itemView.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(400)
            .setStartDelay((position * 50).toLong())
            .start()
    }

    class IncidentViewHolder(
        itemView: View,
        private val onItemClick: (Incident) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val severityChip: Chip = itemView.findViewById(R.id.severityChip)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)

        fun bind(incident: Incident) {
            titleTextView.text = incident.title
            severityChip.text = incident.severity.name
            
            // Set chip color and icon based on severity
            val (chipColor, chipIcon) = when (incident.severity) {
                Severity.LOW -> Pair(R.color.severity_low, R.drawable.ic_alert_medium)
                Severity.MEDIUM -> Pair(R.color.severity_medium, R.drawable.ic_alert_low)
                Severity.HIGH -> Pair(R.color.severity_high, R.drawable.ic_high_alert)
            }
            severityChip.chipBackgroundColor = ContextCompat.getColorStateList(itemView.context, chipColor)
            severityChip.chipIcon = ContextCompat.getDrawable(itemView.context, chipIcon)
            severityChip.isChipIconVisible = true

            // Format date
            val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            isoFormat.timeZone = TimeZone.getTimeZone("UTC")
            val date = isoFormat.parse(incident.reported_at)
            val displayFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            dateTextView.text = date?.let { displayFormat.format(it) } ?: ""

            itemView.setOnClickListener { onItemClick(incident) }
        }
    }

    private class IncidentDiffCallback : DiffUtil.ItemCallback<Incident>() {
        override fun areItemsTheSame(oldItem: Incident, newItem: Incident): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Incident, newItem: Incident): Boolean {
            return oldItem == newItem
        }
    }
} 