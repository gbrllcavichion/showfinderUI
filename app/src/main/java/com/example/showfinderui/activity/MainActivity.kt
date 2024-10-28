package com.example.showfinderui.activity

import com.example.showfinderui.adapter.ConcertAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.showfinderui.R
import com.example.showfinderui.model.Concert

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewFavoriteShows = findViewById<RecyclerView>(R.id.recycler_view_concerts_favorite_shows)
        recyclerViewFavoriteShows.layoutManager = LinearLayoutManager(this)

        val concerts = listOf(
            Concert("Twenty One Pilots", "CWB, SP, RJ", R.drawable.twenty_one_pilots_1200x628)
        )

        recyclerViewFavoriteShows.adapter = ConcertAdapter(concerts) { concert ->
            val intent = Intent(this, ShowDetailsActivity::class.java)
            intent.putExtra("artist_name", concert.artistName)
            intent.putExtra("location", concert.location)
            intent.putExtra("imageResId", concert.imageResId)
            startActivity(intent)
        }

        val recyclerViewOthersShows = findViewById<RecyclerView>(R.id.recycler_view_others_concerts)
        recyclerViewOthersShows.layoutManager = LinearLayoutManager(this)

        val others = listOf(
            Concert("Bring Me The Horizon", "SP", R.drawable.bmth_eventim_222x222),
            Concert("Villamix Festival", "SP",  R.drawable.villamix_lineupp_eventim_profile),
            Concert("Camarote Placar - Lenny Kravitz", "SP",  R.drawable.lennykravitz_camaroteplacar_eventim_profile),
            Concert("Simply Red", "RJ, SP", R.drawable.simplyred_eventim_222x222),
            Concert("Busted", "SP", R.drawable.busted_venue_eventim_222x222),
            Concert("Victoria De Angelis", "SP", R.drawable.victoria_de_angelis_eventim_222x222)

        )

        recyclerViewOthersShows.adapter = ConcertAdapter(others) { concert ->
            val intent = Intent(this, ShowDetailsActivity::class.java)
            intent.putExtra("artist_name", concert.artistName)
            intent.putExtra("location", concert.location)
            intent.putExtra("imageResId", concert.imageResId)
            startActivity(intent)
        }
    }
}
