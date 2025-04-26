package com.example.aiwatchdog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.aiwatchdog.R
import com.example.aiwatchdog.model.Severity
import com.example.aiwatchdog.viewmodel.IncidentViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddIncidentFragment : Fragment() {
    private val viewModel: IncidentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_incident, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Create Alert"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleEditText = view.findViewById<TextInputEditText>(R.id.titleEditText)
        val titleInputLayout = view.findViewById<TextInputLayout>(R.id.titleInputLayout)
        val descriptionEditText = view.findViewById<TextInputEditText>(R.id.descriptionEditText)
        val descriptionInputLayout = view.findViewById<TextInputLayout>(R.id.descriptionInputLayout)
        val severityRadioGroup = view.findViewById<RadioGroup>(R.id.severityRadioGroup)
        val submitButton = view.findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {
            val title = titleEditText.text?.toString()
            val description = descriptionEditText.text?.toString()
            
            var isValid = true

            if (title.isNullOrBlank()) {
                titleInputLayout.error = "Title is required"
                isValid = false
            } else {
                titleInputLayout.error = null
            }

            if (description.isNullOrBlank()) {
                descriptionInputLayout.error = "Description is required"
                isValid = false
            } else {
                descriptionInputLayout.error = null
            }

            if (severityRadioGroup.checkedRadioButtonId == -1) {
                isValid = false
            }

            if (isValid) {
                val severity = when (severityRadioGroup.checkedRadioButtonId) {
                    R.id.lowRadioButton -> Severity.LOW
                    R.id.mediumRadioButton -> Severity.MEDIUM
                    R.id.highRadioButton -> Severity.HIGH
                    else -> Severity.MEDIUM // Default fallback
                }

                viewModel.addIncident(title!!, description!!, severity)
                findNavController().navigateUp()
            }
        }
    }
} 