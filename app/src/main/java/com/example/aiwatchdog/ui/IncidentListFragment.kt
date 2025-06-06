package com.example.aiwatchdog.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.aiwatchdog.R
import com.example.aiwatchdog.adapter.IncidentAdapter
import com.example.aiwatchdog.model.Severity
import com.example.aiwatchdog.viewmodel.IncidentViewModel
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity

class IncidentListFragment : Fragment() {
    private val viewModel: IncidentViewModel by activityViewModels()
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

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Home"
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.incidentsRecyclerView)
        adapter = IncidentAdapter { incident ->
            findNavController().navigate(
                IncidentListFragmentDirections.actionListToDetail(incident.id)
            )
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupSwipeRefresh(view: View) {
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setColorSchemeResources(R.color.purple_500)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadIncidents()
            Handler(Looper.getMainLooper()).postDelayed({
                swipeRefreshLayout.isRefreshing = false
            }, 1000)
        }
    }

    private fun setupFilterChips(view: View) {
        val chipGroup = view.findViewById<ChipGroup>(R.id.filterChipGroup)
        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            for (i in 0 until group.childCount) {
                val chip = group.getChildAt(i)
                chip.animate().scaleX(if (chip.id == checkedId) 1.1f else 1f)
                    .scaleY(if (chip.id == checkedId) 1.1f else 1f)
                    .setDuration(150).start()
            }
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
            findNavController().navigate(
                IncidentListFragmentDirections.actionListToAdd()
            )
        }
    }

    private fun observeViewModel() {
        viewModel.filteredIncidents.observe(viewLifecycleOwner) { incidents ->
            adapter.submitList(incidents)
        }
    }
} 