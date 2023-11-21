package es.jcc.project.MainApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import es.jcc.project.Classes.Equipo
import es.jcc.project.Classes.Jugador
import es.jcc.project.R
import es.jcc.project.databinding.ActivityAppBinding
import es.jcc.project.databinding.FragmentTeamsBinding


class TeamsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentTeamsBinding

    var seleccion: String = ""
    var equips: MutableList<Equipo> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_teams, container, false)

        binding = FragmentTeamsBinding.inflate(inflater, container, false)

        val myAdapter = ArrayAdapter.createFromResource(this.requireContext(), R.array.opciones,
            android.R.layout.simple_spinner_item)
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner2.adapter = myAdapter
        binding.spinner2.onItemSelectedListener = this

        return binding.root
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        seleccion = parent.getItemAtPosition(position).toString()

        if (seleccion == "PRIMERA NACIONAL"){
            equips = mutableListOf<Equipo>(
                Equipo("1", 1)
            )
        }
    }





    override fun onNothingSelected(parent: AdapterView<*>?) {
        //nasin
    }

}