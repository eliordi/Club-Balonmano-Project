package es.jcc.project.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.jcc.project.Classes.Jugador
import es.jcc.project.R

class PlayersAdapter(private val context: Context, private val players: MutableList<Jugador>) : RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_player, parent, false)
        return PlayersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        val player = players[position]
        holder.bindItem(player)
    }

    class PlayersViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvCargo: TextView = view.findViewById(R.id.tvCargo)
        private val tvEdad: TextView = view.findViewById(R.id.tvEdad)

        fun bindItem(p: Jugador){
            tvName.text = p.nombre
            tvCargo.text = p.cargo
            tvEdad.text = p.edad
        }

    }



}