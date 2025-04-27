package com.example.aiwatchdog.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aiwatchdog.R

data class Alert(val title: String, val description: String, val time: String)

class AlertAdapter(private var alerts: List<Alert>) : RecyclerView.Adapter<AlertAdapter.AlertViewHolder>() {
    class AlertViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.alertTitle)
        val description: TextView = view.findViewById(R.id.alertDescription)
        val time: TextView = view.findViewById(R.id.alertTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alert, parent, false)
        return AlertViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlertViewHolder, position: Int) {
        val alert = alerts[position]
        holder.title.text = alert.title
        holder.description.text = alert.description
        holder.time.text = alert.time
    }

    override fun getItemCount() = alerts.size

    fun updateList(newAlerts: List<Alert>) {
        alerts = newAlerts
        notifyDataSetChanged()
    }
}