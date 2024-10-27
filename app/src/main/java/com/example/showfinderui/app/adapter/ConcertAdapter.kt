package com.example.showfinderui.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.showfinderui.app.model.Concert
import com.example.showfinderui.R
import com.example.showfinderui.app.backend.ConcertDetails

class ConcertAdapter(
    private val concertList: List<ConcertDetails>,
    private val onItemClick: (ConcertDetails) -> Unit
) : RecyclerView.Adapter<ConcertAdapter.ConcertViewHolder>() {

    class ConcertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventName: TextView = itemView.findViewById(R.id.artist_name)
        val location: TextView = itemView.findViewById(R.id.location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcertViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_concert, parent, false)
        return ConcertViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConcertViewHolder, position: Int) {
        val concert = concertList[position]
        holder.eventName.text = concert.event
        holder.location.text = concert.location

        holder.itemView.setOnClickListener {
            onItemClick(concert)
        }
    }

    override fun getItemCount(): Int {
        return concertList.size
    }
}
