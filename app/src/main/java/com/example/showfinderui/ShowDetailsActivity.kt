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
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val ticketList = listOf(
            Ticket("CWR","JAN/22, 2025", "QUA", "17H", "Pedreira Paulo Leminski", "R$ 230.00 - R$ 810.00"),
            Ticket("RJ","JAN/24, 2025", "SEX", "18H", "Farmasi Arena", "R$ 165.00 - R$ 810.00"),
            Ticket("SP","JAN/26, 2025", "DOM", "16H", "Allianz Parque", "R$ 200.00 - R$ 810.00")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_tickets)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TicketAdapter(ticketList)

    }
}
