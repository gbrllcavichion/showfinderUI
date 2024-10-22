package com.example.showfinderui

import ConcertAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewFavoriteShows = findViewById<RecyclerView>(R.id.recycler_view_concerts_favorite_shows)
        recyclerViewFavoriteShows.layoutManager = LinearLayoutManager(this)

        val concerts = listOf(
            Concert("Twenty One Pilots", "The Clancy World Tour", "CWB, SP, RJ", ""),
            Concert("Artist name", "Tour name", "Sigla Locais", ""),
            Concert("Artist name", "Tour name", "Sigla Locais", "")
        )

        recyclerViewFavoriteShows.adapter = ConcertAdapter(concerts) { concert ->
            val intent = Intent(this, ShowDetailsActivity::class.java)
            intent.putExtra("artist_name", concert.artistName)
            intent.putExtra("tour_name", concert.tourName)
            intent.putExtra("location", concert.location)
            startActivity(intent)
        }

        val recyclerViewOthersShows = findViewById<RecyclerView>(R.id.recycler_view_others_concerts)
        recyclerViewOthersShows.layoutManager = LinearLayoutManager(this)

        val others = listOf(
            Concert("Artist name", "Tour name", "Sigla Locais", ""),
            Concert("Artist name", "Tour name", "Sigla Locais", ""),
            Concert("Artist name", "Tour name", "Sigla Locais", "")
        )

        recyclerViewOthersShows.adapter = ConcertAdapter(others) { concert ->
            val intent = Intent(this, ShowDetailsActivity::class.java)
            intent.putExtra("artist_name", concert.artistName)
            intent.putExtra("tour_name", concert.tourName)
            intent.putExtra("location", concert.location)
            startActivity(intent)
        }
    }
}
