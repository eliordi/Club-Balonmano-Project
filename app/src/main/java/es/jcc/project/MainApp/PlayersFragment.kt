package es.jcc.project.MainApp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import es.jcc.project.Adapters.PlayersAdapter
import es.jcc.project.Classes.Jugador
import es.jcc.project.Login.WelcomeFragment
import es.jcc.project.R
import es.jcc.project.databinding.ActivityAppBinding
import es.jcc.project.databinding.FragmentPlayersBinding
import java.lang.Exception


class PlayersFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentPlayersBinding

    var seleccion: String = ""
    var jugadors: MutableList<Jugador> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_players, container, false)

        binding = FragmentPlayersBinding.inflate(inflater, container, false)

        val myAdapter = ArrayAdapter.createFromResource(this.requireContext(), R.array.opciones,
            android.R.layout.simple_spinner_item)
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = myAdapter
        binding.spinner.onItemSelectedListener = this

        return binding.root
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        seleccion = parent.getItemAtPosition(position).toString()

        if (seleccion == "PRIMERA NACIONAL"){
            jugadors = mutableListOf<Jugador>(
                Jugador("ANTONI SABATER NACHER", "JUGADOR", "19 ANYS"),
                Jugador("ARTURO GIRBES CAMARASA", "JUGADOR", "23 ANYS"),
                Jugador("DAVID NAVARRO ILLANA", "JUGADOR", "26 ANYS"),
                Jugador("GERARD CARBONELL CLAVER", "JUGADOR", "23 ANYS"),
                Jugador("GUILLEM ADAM PELLICER", "PORTER", "20 ANYS"),
                Jugador("MARC QUINTANA LLANES", "JUGADOR", "20 ANYS"),
                Jugador("MARC ADAM PELLICER", "JUGADOR I CAPITÀ", "24 ANYS"),
                Jugador("NICOLAS SALES LOPEZ", "JUGADOR", "29 ANYS"),
                Jugador("PAU GRANELL MONTAGUD", "JUGADOR", "21 ANYS"),
                Jugador("RAFAEL ADAM ROMEU", "JUGADOR", "22 ANYS"),
                Jugador("SALVADOR SALES LOPEZ", "JUGADOR", "34 ANYS"),
                Jugador("SERGI PELECHANO TEBAR", "JUGADOR", "19 ANYS"),
                Jugador("ALEX ALANDETE CLIMENT", "JUGADOR", "20 ANYS"),
                Jugador("ANDREI CRISTIAN DUMITRACHE SERBAN", "JUGADOR", "21 ANYS"),
                Jugador("ANDREU JAIME TRULL", "PORTER", "19 ANYS"),
                Jugador("DANIEL CESPEDES GUTIERREZ", "PORTER", "16 ANYS"),
                Jugador("HUGO ALARCON FERRIS", "JUGADOR", "16 ANYS"),
                Jugador("IKER TRULL ARMENGOL", "JUGADOR", "20 ANYS"),
                Jugador("JOAN RIBES FERRER", "JUGADOR", "20 ANYS"),
                Jugador("JORDI CAMARASA CAMARASA", "JUGADOR", "19 ANYS"),
                Jugador("PAU DIEZ MORALES", "JUGADOR", "16 ANYS"),
                Jugador("RODRIGO ALBA GOMEZ", "JUGADOR", "16 ANYS"),
                Jugador("VICENT ENRIC NOGUES ARGENTE", "ENTRENADOR", "43 ANYS"),
                Jugador("JOSE CARRASCO ALIAGA", "PRESIDENT", "56 ANYS"),
                Jugador("JOSE DAVID TRULL CLIMENT", "OFICIAL", "53 ANYS"),
                Jugador("LUIS FERRANDO GARCIA", "OFICIAL", "50 ANYS")
            )
            setUpReyclerView(jugadors)
        }else{
            jugadors = mutableListOf<Jugador>(
                Jugador("ALEX ALANDETE CLIMENT", "JUGADOR", "20 ANYS"),
                Jugador("ANDREI CRISTIAN DUMITRACHE SERBAN", "JUGADOR", "21 ANYS"),
                Jugador("ANDREU JAIME TRULL", "PORTER", "19 ANYS"),
                Jugador("ANDREU PENADES PLA", "JUGADOR", "19 ANYS"),
                Jugador("ENRIC ARNAU BISBAL LLOBREGAT", "PORTER", "22 ANYS"),
                Jugador("HECTOR FERRER GARCIA", "JUGADOR", "33 ANYS"),
                Jugador("IKER TRULL ARMENGOL", "JUGADOR", "20 ANYS"),
                Jugador("JAVIER BORRAS BLASCO", "PORTER", "31 ANYS"),
                Jugador("JOAN RIBES FERRER", "JUGADOR", "20 ANYS"),
                Jugador("JORDI CAMARASA CAMARASA", "JUGADOR", "19 ANYS"),
                Jugador("JOSEP CHINESTA CERVERO", "JUGADOR", "20 ANYS"),
                Jugador("JUAN CARLOS CARO GARCIA", "JUGADOR", "24 ANYS"),
                Jugador("LLORENS ADAM GREGORI", "JUGADOR", "18 ANYS"),
                Jugador("LLUIS PEREZ LORENTE", "JUGADOR", "20 ANYS"),
                Jugador("MARC ADAM MANZANO", "JUGADOR", "19 ANYS"),
                Jugador("NICOLAS GINER SAURINA", "JUGADOR", "20 ANYS"),
                Jugador("SALVADOR BALAGUER TRULL", "JUGADOR I CAPITÀ", "34 ANYS"),
                Jugador("SERGI FUSTER MERINO", "JUGADOR", "22 ANYS"),
                Jugador("VICENT ARNANDIS VALLES", "JUGADOR", "32 ANYS"),
                Jugador("ADRIAN RIPOLL SANCHIS", "JUGADOR", "17 ANYS"),
                Jugador("ALVARO ADAM FERRAGUD", "JUGADOR", "17 ANYS"),
                Jugador("JORDI JAIME TRULL", "PORTER", "17 ANYS"),
                Jugador("ADRIAN MACHI ALANDETE", "ENTRENADOR", "40 ANYS"),
                Jugador("JOSE CARRASCO ALIAGA", "PRESIDENT", "56 ANYS"),
                Jugador("PASCUAL NEGRE MARTINEZ", "OFICIAL", "34 ANYS"),
                Jugador("JOSE DAVID TRULL CLIMENT", "STAFF ADICIONAL", "53 ANYS")
            )
            setUpReyclerView(jugadors)
        }
    }

    private fun setUpReyclerView(jugadors: MutableList<Jugador>) {
        val playerAdapter = PlayersAdapter(this.requireContext(), jugadors)
        binding.recView.adapter = playerAdapter
        binding.recView.layoutManager = LinearLayoutManager(this.requireContext(),
            LinearLayoutManager.VERTICAL, false)
        binding.recView.addItemDecoration(DividerItemDecoration(this.requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //nasin
    }


}