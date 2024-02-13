package es.jcc.project.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.jcc.project.API.Artwork
import es.jcc.project.R

class ArtAdapter(private val context: Context,
                 private var artworks: MutableList<Artwork>)
    : RecyclerView.Adapter<ArtAdapter.ArtViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_artwork, parent, false)
        return ArtViewHolder(view)
    }

    override fun getItemCount(): Int {
        return artworks.size
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        val artwork = artworks[position]
        holder.bindItem(artwork)
    }

    fun setData(artworks: List<Artwork>) {
        this.artworks = artworks.toMutableList()
        notifyDataSetChanged()
    }
    class ArtViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageView: ImageView = view.findViewById(R.id.imageViewArtwork)
        private val tvName: TextView = view.findViewById(R.id.textViewArtworkName)
        private val tvArtist: TextView = view.findViewById(R.id.textViewArtistName)

        fun bindItem(a: Artwork) {
            Picasso.get().load(a.imageUrl.url).into(imageView)
            tvName.text = a.title
            tvArtist.text = a.artists.joinToString { it.name }
        }
    }
}
