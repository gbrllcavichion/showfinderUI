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

    private var areAllNotificationsEnabled = false
    private val selectedCities = mutableSetOf<String>()
    private val allCities = listOf("CWR", "RJ", "SP")

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
        val imageResId = intent.getIntExtra("imageResId", R.drawable.menu_gallery_24)

        val artistNameTextView = findViewById<TextView>(R.id.tv_artist_name)
        val showImageView = findViewById<ImageView>(R.id.iv_show_image)
        val bellImageView = findViewById<ImageView>(R.id.iv_bell)

        artistNameTextView.text = artistName

        Glide.with(this)
            .load(imageResId)
            .placeholder(R.drawable.menu_gallery_24)
            .into(showImageView)

        bellImageView.setOnClickListener {
            toggleAllNotifications(bellImageView)
        }

        val ticketList = listOf(
            Ticket("CWR", "JAN/22, 2025", "QUA", "17H", "Pedreira Paulo Leminski", "R$ 230.00 - R$ 810.00"),
            Ticket("RJ", "JAN/24, 2025", "SEX", "18H", "Farmasi Arena", "R$ 165.00 - R$ 810.00"),
            Ticket("SP", "JAN/26, 2025", "DOM", "16H", "Allianz Parque", "R$ 200.00 - R$ 810.00")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_tickets)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TicketAdapter(ticketList, ::toggleCityNotification)
    }

    private fun toggleAllNotifications(bellImageView: ImageView) {
        areAllNotificationsEnabled = !areAllNotificationsEnabled

        bellImageView.setImageResource(
            if (areAllNotificationsEnabled) R.drawable.notifications_filled_24 else R.drawable.notifications_24
        )

        selectedCities.clear()
        if (areAllNotificationsEnabled) {
            selectedCities.addAll(allCities)
        }

        findViewById<RecyclerView>(R.id.recycler_view_tickets).adapter?.notifyDataSetChanged()
    }

    private fun toggleCityNotification(city: String, bellImageView: ImageView) {
        if (selectedCities.contains(city)) {
            selectedCities.remove(city)
            bellImageView.setImageResource(R.drawable.notifications_24)
        } else {
            selectedCities.add(city)
            bellImageView.setImageResource(R.drawable.notifications_filled_24)
        }

        val allSelected = selectedCities.size == allCities.size
        val mainBell = findViewById<ImageView>(R.id.iv_bell)
        mainBell.setImageResource(if (allSelected) R.drawable.notifications_filled_24 else R.drawable.notifications_24)
        areAllNotificationsEnabled = allSelected
    }
}
