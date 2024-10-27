package com.example.showfinderui.app.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import net.openid.appauth.*
import com.example.showfinderui.R
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private lateinit var authService: AuthorizationService
    private lateinit var authState: AuthState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val serviceConfig = AuthorizationServiceConfiguration(
            Uri.parse("https://accounts.spotify.com/authorize"),
            Uri.parse("https://accounts.spotify.com/api/token")
        )

        val clientId = "f9f04173d82b4a7fafc1fc7e45b2083f"
        val redirectUri = Uri.parse("http://localhost:8080/login/oauth2/code/spotify")

        val request = AuthorizationRequest.Builder(
            serviceConfig,
            clientId,
            ResponseTypeValues.CODE,
            redirectUri
        ).setScope("user-top-read")
            .build()

        authService = AuthorizationService(this)

        val authIntent = authService.getAuthorizationRequestIntent(request)
        startActivityForResult(authIntent, AUTH_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AUTH_REQUEST_CODE) {
            val response = AuthorizationResponse.fromIntent(data!!)
            val ex = AuthorizationException.fromIntent(data)

            if (response != null) {
                authState = AuthState(response, ex)
                exchangeAuthorizationCode(response)
            } else {
                Toast.makeText(this, "Falha na autenticação", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun exchangeAuthorizationCode(authorizationResponse: AuthorizationResponse) {
        val tokenRequest = authorizationResponse.createTokenExchangeRequest()

        authService.performTokenRequest(
            tokenRequest
        ) { tokenResponse, tokenException ->
            if (tokenResponse != null) {
                authState.update(tokenResponse, tokenException)

                val accessToken = tokenResponse.accessToken

                val rawResponse = tokenResponse.jsonSerializeString()
                Log.d("RAW_JSON_RESPONSE", rawResponse)

                Toast.makeText(this, "Token de acesso: $accessToken", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Falha ao obter o token", Toast.LENGTH_SHORT).show()
            }
        }
    }


    companion object {
        private const val AUTH_REQUEST_CODE = 100
    }
}