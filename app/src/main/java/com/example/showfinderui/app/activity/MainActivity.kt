package com.example.showfinderui.app.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.showfinderui.R
import com.example.showfinderui.app.adapter.ConcertAdapter
import com.example.showfinderui.app.backend.RetrofitClient
import com.example.showfinderui.app.model.Concert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        val accessToken = "Bearer seu_token_de_acesso"
        fetchFavoriteShows(accessToken, recyclerView)
    }

    private fun fetchFavoriteShows(accessToken: String, recyclerView: RecyclerView) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val shows = RetrofitClient.apiService.getFavoriteShows(accessToken)

                val concerts = shows.map { concertDetail ->
                    Concert(
                        artistName = concertDetail.event,
                        tourName = "Tour Name Placeholder",
                        location = "Location Placeholder",
                        imageUrl = "URL Placeholder"
                    )
                }

                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Shows obtidos: ${shows.size}", Toast.LENGTH_SHORT).show()

                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView.adapter = ConcertAdapter(shows) { concertDetails ->
                        Toast.makeText(this@MainActivity, "Clicou no show: ${concertDetails.event}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Erro ao obter shows", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}