package com.example.showfinderui.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.showfinderui.R
import com.example.showfinderui.adapter.TicketAdapter
import com.example.showfinderui.model.Ticket

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

        val artistName = intent.getStringExtra("artist_name")
        val location = intent.getStringExtra("location")
        val imageUrl = intent.getStringExtra("image_url")

        val artistNameTextView = findViewById<TextView>(R.id.tv_artist_name)
        val showImageView = findViewById<ImageView>(R.id.iv_show_image)

        artistNameTextView.text = artistName

        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.menu_gallery_24)
            .into(showImageView)

        val ticketList = if (artistName == "Bring Me The Horizon") {
            listOf(
                Ticket("SP", "30 November 2024", "SAT", "13:00", "Allianz Parque", "R$ 230.00 - R$ 810.00")
            )
        } else {
            listOf(
                Ticket("CWR", "JAN/22, 2025", "QUA", "17H", "Pedreira Paulo Leminski", "R$ 230.00 - R$ 810.00"),
                Ticket("RJ", "JAN/24, 2025", "SEX", "18H", "Farmasi Arena", "R$ 165.00 - R$ 810.00"),
                Ticket("SP", "JAN/26, 2025", "DOM", "16H", "Allianz Parque", "R$ 200.00 - R$ 810.00")
            )
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_tickets)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TicketAdapter(ticketList)
    }
}
