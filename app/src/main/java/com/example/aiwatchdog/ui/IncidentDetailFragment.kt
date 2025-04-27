package com.example.aiwatchdog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.aiwatchdog.R
import com.example.aiwatchdog.model.Severity
import com.example.aiwatchdog.viewmodel.IncidentViewModel
import com.google.android.material.chip.Chip
import java.text.SimpleDateFormat
import java.util.*

class IncidentDetailFragment : Fragment() {
    private val viewModel: IncidentViewModel by activityViewModels()
    private val args: IncidentDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_incident_detail, container, false)

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Incident Details"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.incidents.observe(viewLifecycleOwner) { incidents ->
            val incident = incidents.find { it.id == args.incidentId }
            incident?.let {
                view.findViewById<TextView>(R.id.detailTitle).text = it.title
                view.findViewById<TextView>(R.id.detailDescription).text = it.description
                val chip = view.findViewById<Chip>(R.id.detailSeverity)
                chip.text = it.severity.name
                chip.setChipBackgroundColorResource(
                    when (it.severity) {
                        Severity.LOW -> R.color.severity_low
                        Severity.MEDIUM -> R.color.severity_medium
                        Severity.HIGH -> R.color.severity_high
                    }
                )
                val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                isoFormat.timeZone = TimeZone.getTimeZone("UTC")
                val date = isoFormat.parse(it.reported_at)
                val displayFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                view.findViewById<TextView>(R.id.detailDate).text = date?.let { displayFormat.format(it) } ?: ""
            }
        }
        val icon = view.findViewById<ImageView>(R.id.incidentIcon)
        val title = view.findViewById<TextView>(R.id.detailTitle)
        val chip = view.findViewById<Chip>(R.id.detailSeverity)
        val card = view.findViewById<View>(R.id.descriptionCard)

        val viewsToAnimate = listOf(icon, title, chip, card)
        viewsToAnimate.forEachIndexed { i, v ->
            v.alpha = 0f
            v.animate()
                .alpha(1f)
                .setDuration(400)
                .setStartDelay((i * 100).toLong())
                .start()
        }
        card.translationY = 40f
        card.animate()
            .translationY(0f)
            .setDuration(400)
            .setStartDelay(300)
            .start()
    }
}