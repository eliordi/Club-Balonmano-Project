package es.jcc.project.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.jcc.project.Classes.Equipo
import es.jcc.project.Classes.Jugador
import es.jcc.project.R
class EquiposAdapter(private val context: Context, private val equipos: MutableList<Equipo>) : RecyclerView.Adapter<EquiposAdapter.EquiposViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquiposViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_team, parent, false)
        return EquiposViewHolder(view)
    }

    override fun onBindViewHolder(holder: EquiposViewHolder, position: Int) {
        val equip = equipos[position]
        holder.bindItem(equip)
    }

    override fun getItemCount(): Int {
        return equipos.size
    }

    class EquiposViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val tvPosicio: TextView = view.findViewById(R.id.tvPosicion)
        private val imatge: ImageView = view.findViewById(R.id.image)
        private val tvNom: TextView = view.findViewById(R.id.tvNom)
        private val tvPunts: TextView = view.findViewById(R.id.tvPunts)
        private val tvJugats: TextView = view.findViewById(R.id.tvJugats)
        private val tvGuanyats: TextView = view.findViewById(R.id.tvGuanyats)
        private val tvEmpatats: TextView = view.findViewById(R.id.tvEmpatats)
        private val tvPerduts: TextView = view.findViewById(R.id.tvPerduts)

        fun bindItem(e: Equipo){
            tvPosicio.text = e.posicio
            imatge.setImageResource(e.imatge)
            tvNom.text = e.nom
            tvPunts.text = e.punts
            tvJugats.text = e.jugats
            tvGuanyats.text = e.guanyats
            tvEmpatats.text = e.empatats
            tvPerduts.text = e.perduts
        }

    }


}