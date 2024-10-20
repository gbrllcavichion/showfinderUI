package com.example.showfinderui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShowDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)

        val artistName = intent.getStringExtra("artist_name")
        val tourName = intent.getStringExtra("tour_name")
        val location = intent.getStringExtra("location")

        val tvArtistName: TextView = findViewById(R.id.tv_artist_name)
        val tvTourName: TextView = findViewById(R.id.tv_tour_name)
        val tvLocation: TextView = findViewById(R.id.tv_show_location)

        tvArtistName.text = artistName
        tvTourName.text = tourName
        tvLocation.text = location
    }
}
