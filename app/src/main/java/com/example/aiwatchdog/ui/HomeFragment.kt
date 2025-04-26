package com.example.aiwatchdog.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aiwatchdog.R

class HomeFragment : Fragment() {

    private lateinit var adapter: AlertAdapter
    private val allAlerts = listOf(
        Alert("System Update", "Scheduled update at 2 AM.", "5 minutes ago"),
        Alert("New Login", "Login detected from New York.", "2 hours ago"),
        Alert("Payment Received", "Your payment of \$120 was successful.", "Yesterday"),
        Alert("Password Changed", "Your password was changed successfully.", "2 days ago"),
        Alert("Low Storage", "Your device storage is below 10%.", "3 days ago")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.alertsRecyclerView)
        val searchEditText = view.findViewById<EditText>(R.id.searchEditText)
        adapter = AlertAdapter(allAlerts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val filtered = allAlerts.filter {
                    it.title.contains(s.toString(), ignoreCase = true) ||
                    it.description.contains(s.toString(), ignoreCase = true)
                }
                adapter.updateList(filtered)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}

data class Alert(val title: String, val description: String, val time: String) 