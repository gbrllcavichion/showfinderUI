package com.example.showfinderui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShowDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val ticketList = listOf(
            Ticket("JAN/22, 2025", "17H", "Pedreira Paulo Leminski", "R$ 0.00 - R$ 10.00", "Vendido por: Eventim"),
            Ticket("JAN/22, 2025", "17H", "Pedreira Paulo Leminski", "R$ 0.00 - R$ 10.00", "Vendido por: Eventim")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_tickets)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TicketAdapter(ticketList)

    }
}
