package es.jcc.project.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.jcc.project.Classes.Jugador
import es.jcc.project.Classes.Noticia
import es.jcc.project.R

class NoticiasAdapter (private val context: Context, private val noticias: MutableList<Noticia>) : RecyclerView.Adapter<NoticiasAdapter.NoticiasViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiasViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_notice, parent, false)
        return NoticiasViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticiasViewHolder, position: Int) {
        val notice = noticias[position]
        holder.bindItem(notice)
    }

    override fun getItemCount(): Int {
        return noticias.size
    }

    class NoticiasViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val image: ImageView = view.findViewById(R.id.imageNoticia)
        private val texto: TextView = view.findViewById(R.id.tvNoticiaText)

        fun bindItem(n: Noticia){
            image.setImageResource(n.image)
            texto.text = n.texto
        }

    }


}