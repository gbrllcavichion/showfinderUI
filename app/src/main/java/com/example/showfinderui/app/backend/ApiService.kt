package com.example.showfinderui.app.backend

import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("/api/shows/match-favorite-shows")
    suspend fun getFavoriteShows(
        @Header("Authorization") token: String
    ): List<ConcertDetails>
}
