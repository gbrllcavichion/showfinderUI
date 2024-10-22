import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.showfinderui.Concert
import com.example.showfinderui.R

class ConcertAdapter(
    private val concertList: List<Concert>,
    private val onItemClick: (Concert) -> Unit
) : RecyclerView.Adapter<ConcertAdapter.ConcertViewHolder>() {

    class ConcertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val artistName: TextView = itemView.findViewById(R.id.artist_name)
        val tourName: TextView = itemView.findViewById(R.id.tour_name)
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
        holder.tourName.text = concert.tourName
        holder.location.text = concert.location

        holder.itemView.setOnClickListener {
            onItemClick(concert)
        }

        if (position == 0) {
            Glide.with(holder.concertImage.context)
                .load(R.drawable.twenty_one_pilots_1200x628)
                .placeholder(R.drawable.menu_gallery_24)
                .error(R.drawable.menu_gallery_24)
                .into(holder.concertImage)
            holder.concertImage.visibility = View.VISIBLE
        } else {
            Glide.with(holder.concertImage.context)
                .load(R.drawable.menu_gallery_24)
                .placeholder(R.drawable.menu_gallery_24)
                .error(R.drawable.menu_gallery_24)
                .into(holder.concertImage)
            holder.concertImage.visibility = View.VISIBLE
        }
    }


    override fun getItemCount(): Int {
        return concertList.size
    }
}
