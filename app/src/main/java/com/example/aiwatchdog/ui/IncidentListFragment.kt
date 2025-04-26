package com.example.aiwatchdog.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.aiwatchdog.R
import com.example.aiwatchdog.adapter.IncidentAdapter
import com.example.aiwatchdog.model.Severity
import com.example.aiwatchdog.viewmodel.IncidentViewModel
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

class IncidentListFragment : Fragment() {
    private val viewModel: IncidentViewModel by viewModels()
    private lateinit var adapter: IncidentAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_incident_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(view)
        setupFilterChips(view)
        setupSwipeRefresh(view)
        setupFab(view)
        observeViewModel()
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.incidentsRecyclerView)
        adapter = IncidentAdapter { incident ->
            // TODO: Navigate to detail view
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupSwipeRefresh(view: View) {
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setColorSchemeResources(R.color.purple_500)
        swipeRefreshLayout.setOnRefreshListener {
            // Simulate refresh
            viewModel.loadIncidents()
            Handler(Looper.getMainLooper()).postDelayed({
                swipeRefreshLayout.isRefreshing = false
            }, 1000)
        }
    }

    private fun setupFilterChips(view: View) {
        val chipGroup = view.findViewById<ChipGroup>(R.id.filterChipGroup)
        chipGroup.setOnCheckedChangeListener { _, checkedId ->
            val severity = when (checkedId) {
                R.id.chipLow -> Severity.LOW
                R.id.chipMedium -> Severity.MEDIUM
                R.id.chipHigh -> Severity.HIGH
                else -> null
            }
            viewModel.applyFilter(severity)
        }
    }

    private fun setupFab(view: View) {
        view.findViewById<FloatingActionButton>(R.id.fabAddIncident).setOnClickListener {
            // TODO: Navigate to add incident screen
        }
    }

    private fun observeViewModel() {
        viewModel.filteredIncidents.observe(viewLifecycleOwner) { incidents ->
            adapter.submitList(incidents)
        }
    }
} 