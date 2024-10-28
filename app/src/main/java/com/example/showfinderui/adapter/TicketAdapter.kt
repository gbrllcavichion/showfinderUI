package com.example.showfinderui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.showfinderui.R
import com.example.showfinderui.model.Ticket

class TicketAdapter(
    private val ticketList: List<Ticket>,
    private val areAllNotificationsEnabled: Boolean,
    private val onNotificationToggle: (String, ImageView) -> Unit
) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ticketDate: TextView = itemView.findViewById(R.id.tv_ticket_date)
        val locationAbbreviation: TextView = itemView.findViewById(R.id.tv_location_abbreviation)
        val dayOfWeek: TextView = itemView.findViewById(R.id.tv_day_of_week)
        val ticketTime: TextView = itemView.findViewById(R.id.tv_ticket_time)
        val ticketLocation: TextView = itemView.findViewById(R.id.tv_ticket_location)
        val ticketPrice: TextView = itemView.findViewById(R.id.tv_ticket_price)
        val bellImageView: ImageView = itemView.findViewById(R.id.iv_bell)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket, parent, false)
        return TicketViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = ticketList[position]
        holder.ticketDate.text = ticket.date
        holder.locationAbbreviation.text = ticket.abbreviation
        holder.dayOfWeek.text = ticket.weekDay
        holder.ticketTime.text = ticket.time
        holder.ticketLocation.text = ticket.location
        holder.ticketPrice.text = ticket.price

        holder.bellImageView.setImageResource(
            if (areAllNotificationsEnabled) R.drawable.notifications_filled_24 else R.drawable.notifications_24
        )

        holder.bellImageView.setOnClickListener {
            onNotificationToggle(ticket.abbreviation, holder.bellImageView)
        }
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }
}
