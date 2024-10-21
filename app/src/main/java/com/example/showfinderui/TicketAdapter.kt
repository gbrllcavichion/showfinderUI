package com.example.showfinderui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TicketAdapter(private val ticketList: List<Ticket>) :
    RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ticketDate: TextView = itemView.findViewById(R.id.tv_ticket_date)
        val ticketTime: TextView = itemView.findViewById(R.id.tv_ticket_time)
        val ticketLocation: TextView = itemView.findViewById(R.id.tv_ticket_location)
        val ticketPrice: TextView = itemView.findViewById(R.id.tv_ticket_price)
        val ticketSeller: TextView = itemView.findViewById(R.id.tv_ticket_seller)
        val arrowIcon: ImageView = itemView.findViewById(R.id.iv_arrow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket, parent, false)
        return TicketViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = ticketList[position]
        holder.ticketDate.text = ticket.date
        holder.ticketTime.text = ticket.time
        holder.ticketLocation.text = ticket.location
        holder.ticketPrice.text = ticket.price
        holder.ticketSeller.text = ticket.seller
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }
}
