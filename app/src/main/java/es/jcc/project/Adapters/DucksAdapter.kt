package es.jcc.project.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.jcc.project.R

class DucksAdapter(private val context: Context,
                   private var ducks: MutableList<String>)
    : RecyclerView.Adapter<DucksAdapter.DuckViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DuckViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_duck, parent, false)
        return DuckViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ducks.size
    }

    override fun onBindViewHolder(holder: DuckViewHolder, position: Int) {
        val imageUrl = ducks[position]
        holder.bindItem(imageUrl)
    }

    class DuckViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.imageViewDuck)
        fun bindItem(imageUrl: String) {
            Picasso.get().load(imageUrl).into(imageView)

        }
    }
}
