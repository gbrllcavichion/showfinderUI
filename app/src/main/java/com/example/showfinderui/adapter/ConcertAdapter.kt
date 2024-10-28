package com.example.showfinderui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.showfinderui.model.Concert
import com.example.showfinderui.R
import com.example.showfinderui.activity.ShowDetailsActivity

class ConcertAdapter(
    private val concertList: List<Concert>,
    private val onItemClick: (Concert) -> Unit
) : RecyclerView.Adapter<ConcertAdapter.ConcertViewHolder>() {

    class ConcertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val artistName: TextView = itemView.findViewById(R.id.artist_name)
        val location: TextView = itemView.findViewById(R.id.location)
        val concertImage: ImageView = itemView.findViewById(R.id.image_concert)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcertViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_concert, parent, false)
        return ConcertViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConcertViewHolder, position: Int) {
        val concert = concertList[position]
        holder.artistName.text = concert.artistName
        holder.location.text = concert.location

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ShowDetailsActivity::class.java)
            intent.putExtra("artist_name", concert.artistName)
            intent.putExtra("location", concert.location)
            intent.putExtra("image_url", concert.imageUrl)
            holder.itemView.context.startActivity(intent)
        }

        Glide.with(holder.concertImage.context)
            .load(concert.imageUrl)
            .placeholder(R.drawable.menu_gallery_24)
            .error(R.drawable.menu_gallery_24)
            .into(holder.concertImage)

        if (concert.imageUrl.isNotEmpty()) {
            try {
                Glide.with(holder.concertImage.context)
                    .load(concert.imageUrl.toInt())
                    .placeholder(R.drawable.menu_gallery_24)
                    .error(R.drawable.menu_gallery_24)
                    .into(holder.concertImage)
            } catch (e: NumberFormatException) {
                Glide.with(holder.concertImage.context)
                    .load(concert.imageUrl)
                    .placeholder(R.drawable.menu_gallery_24)
                    .error(R.drawable.menu_gallery_24)
                    .into(holder.concertImage)
            }
        } else {
            Glide.with(holder.concertImage.context)
                .load(R.drawable.menu_gallery_24)
                .into(holder.concertImage)
        }
    }

    override fun getItemCount(): Int {
        return concertList.size
    }
}
