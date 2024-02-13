package es.jcc.project.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import es.jcc.project.Classes.Mensaje
import es.jcc.project.MainApp.ChatFragment
import es.jcc.project.Managers.AuthManager
import es.jcc.project.R

class MensajesAdapter (private val context: Context,
                       private val mensajes: MutableList<Mensaje>,
                       private val mensajeClickedListener: OnMensajeCLickedListener)
    : RecyclerView.Adapter<MensajesAdapter.MensajesViewHolder>(){

    @SuppressLint("MissingInflatedId")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensajesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_mensaje, parent, false)

        val prefs = context.getSharedPreferences("es.jcc.project_preferences", Context.MODE_PRIVATE)
        val mcolor = prefs.getInt("chatColor", 0)
        val tcolor = prefs.getInt("textColor", 0)
        view.findViewById<CardView>(R.id.cardViewMensaje).setCardBackgroundColor(mcolor)
        view.findViewById<TextView>(R.id.tvText).setTextColor(tcolor)


        return MensajesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MensajesViewHolder, position: Int) {
        val mensaje = mensajes[position]
        holder.bindItem(mensaje)
        holder.itemView.setOnLongClickListener { mensajeClickedListener.onMensajeLongClicked(mensaje)}
    }

    override fun getItemCount(): Int {
        return mensajes.size
    }

    class MensajesViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val tvEmail: TextView = view.findViewById(R.id.tvEmail)
        private val tvText: TextView = view.findViewById(R.id.tvText)

        fun bindItem(m: Mensaje){
            tvEmail.text = m.email
            tvText.text = m.text
        }
    }

    interface OnMensajeCLickedListener{
        fun onMensajeLongClicked(mensaje: Mensaje): Boolean
    }


}