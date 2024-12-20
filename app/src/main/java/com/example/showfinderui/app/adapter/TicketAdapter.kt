package com.example.showfinderui.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.showfinderui.R
import com.example.showfinderui.app.model.Ticket

class TicketAdapter(private val ticketList: List<Ticket>) :
    RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ticketDate: TextView = itemView.findViewById(R.id.tv_ticket_date)
        val locationAbbreviation: TextView = itemView.findViewById(R.id.tv_location_abbreviation)
        val dayOfWeek: TextView = itemView.findViewById(R.id.tv_day_of_week)
        val ticketTime: TextView = itemView.findViewById(R.id.tv_ticket_time)
        val ticketLocation: TextView = itemView.findViewById(R.id.tv_ticket_location)
        val ticketPrice: TextView = itemView.findViewById(R.id.tv_ticket_price)
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
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }
}
