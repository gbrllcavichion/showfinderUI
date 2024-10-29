package com.example.showfinderui.activity

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.showfinderui.R
import com.example.showfinderui.adapter.TicketAdapter
import com.example.showfinderui.model.Ticket

private const val CHANNEL_ID = "ticket_notifications"
private const val REQUEST_NOTIFICATION_PERMISSION = 1

class ShowDetailsActivity : AppCompatActivity() {

    private var areAllNotificationsEnabled = false
    private val selectedCities = mutableSetOf<String>()
    private val allCities = listOf("CWR", "RJ", "SP")
    private lateinit var ticketAdapter: TicketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)

        createNotificationChannel()

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

        val ticketList = if (artistName == "Bring Me The Horizon") {
            listOf(
                Ticket("SP", "NOV/30, 2024", "SÁB", "13H", "Allianz Parque", "R$ 125.00 - R$ 790.00")
            )
        } else {
            listOf(
                Ticket("CWR", "JAN/22, 2025", "QUA", "17H", "Pedreira Paulo Leminski", "R$ 230.00 - R$ 810.00"),
                Ticket("RJ", "JAN/24, 2025", "SEX", "18H", "Farmasi Arena", "R$ 165.00 - R$ 810.00"),
                Ticket("SP", "JAN/26, 2025", "DOM", "16H", "Allianz Parque", "R$ 200.00 - R$ 810.00")
            )
        }

        ticketAdapter = TicketAdapter(ticketList, areAllNotificationsEnabled, ::toggleCityNotification)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_tickets)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ticketAdapter
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notificações de Ingressos"
            val descriptionText = "Notificações para ingressos disponíveis nas cidades selecionadas"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun toggleAllNotifications(bellImageView: ImageView) {
        areAllNotificationsEnabled = !areAllNotificationsEnabled

        bellImageView.setImageResource(
            if (areAllNotificationsEnabled) R.drawable.notifications_filled_24 else R.drawable.notifications_24
        )

        selectedCities.clear()
        if (areAllNotificationsEnabled) {
            selectedCities.addAll(allCities)
            checkNotificationPermissionAndSend("CWR")
        }

        ticketAdapter.updateAllNotifications(areAllNotificationsEnabled)
    }

    private fun toggleCityNotification(city: String, bellImageView: ImageView) {
        if (selectedCities.contains(city)) {
            selectedCities.remove(city)
            bellImageView.setImageResource(R.drawable.notifications_24)
        } else {
            selectedCities.add(city)
            bellImageView.setImageResource(R.drawable.notifications_filled_24)
            if (city == "CWR") {
                checkNotificationPermissionAndSend("CWR")
            }
        }

        val allSelected = selectedCities.size == allCities.size
        val mainBell = findViewById<ImageView>(R.id.iv_bell)
        mainBell.setImageResource(if (allSelected) R.drawable.notifications_filled_24 else R.drawable.notifications_24)
        areAllNotificationsEnabled = allSelected
    }


    private fun sendNotification(city: String) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notifications_filled_24)
            .setContentTitle("Ingressos Disponíveis")
            .setContentText("Pista Normal $city tem ingressos disponíveis")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(city.hashCode(), notificationBuilder.build())
        }
    }

    private fun checkNotificationPermissionAndSend(city: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_NOTIFICATION_PERMISSION
                )
            } else {
                sendNotification(city)
            }
        } else {
            sendNotification(city)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_NOTIFICATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendNotification("CWR")
            } else {
                Toast.makeText(this, "Permissão para enviar notificações foi negada.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}