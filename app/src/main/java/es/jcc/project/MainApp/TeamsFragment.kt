package es.jcc.project.MainApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import es.jcc.project.Adapters.EquiposAdapter
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
                Equipo("1", R.drawable.canterasur, "CBM CANTERA SUR", "18", "10", "9", "0", "1"),
                Equipo("2", R.drawable.elda, "BM. ELDA C.E.E.", "16", "9", "8", "0", "1"),
                Equipo("3", R.drawable.granollers, "KH-7 BM. GRANOLLERS", "14", "9", "7", "0", "2"),
                Equipo("4", R.drawable.elche, "ATTICGO BM ELCHE", "12", "9", "6", "0", "3"),
                Equipo("5", R.drawable.aguilas, "RITEC BM AGUILAS", "11", "9", "5", "1", "3"),
                Equipo("6", R.drawable.mislata, "C.BM. MISLATA", "10", "8", "5", "0", "3"),
                Equipo("7", R.drawable.marni, "LEVANTE UDBM MARNI", "9", "9", "4", "1", "4"),
                Equipo("8", R.drawable.torrevieja, "C.BM. MARE NOSTRUM TORREVIEJA", "8", "9", "4", "0", "5"),
                Equipo("9", R.drawable.puertosagunto, "FERTIBERIA PUERTO SAGUNTO B", "7", "8", "3", "1", "4"),
                Equipo("10", R.drawable.murcia, "UCAM BALONMANO MURCIA", "6", "8", "3", "0", "5"),
                Equipo("11", R.drawable.maristes, "CB. MARISTAS ALGEMESI", "6", "9", "3", "0", "6"),
                Equipo("12", R.drawable.abaranera, "AGRINGENIA-ASOC.ABARANERA", "5", "8", "2", "1", "5"),
                Equipo("13", R.drawable.santjoan, "HANDBOL SANT JOAN", "0", "8", "0", "0", "8"),
                Equipo("14", R.drawable.benidorm, "BM. SERVIGROUP BENIDORM", "0", "9", "0", "0", "9"),
            )
            setUpReyclerView(equips)
        }else{
            equips = mutableListOf<Equipo>(
                Equipo("1", R.drawable.petrer, "HISPANITAS BM PETRER", "14", "7", "7", "0", "0"),
                Equipo("2", R.drawable.agustinos, "FUNDACION AGUSTINOS ALICANTE", "10", "6", "5", "0", "1"),
                Equipo("3", R.drawable.torrellano, "TORRELLANO HC TORREBANDA", "10", "7", "5", "0", "2"),
                Equipo("4", R.drawable.almoradi, "C.BM. ALMORADI", "10", "7", "5", "0", "2"),
                Equipo("5", R.drawable.castello, "BM. CASTELLON", "8", "7", "4", "0", "3"),
                Equipo("6", R.drawable.lliria, "C.BM. LLIRIA A", "8", "7", "4", "0", "3"),
                Equipo("7", R.drawable.pilar, "UPV-EL PILAR VALENCIA", "8", "7", "4", "0", "3"),
                Equipo("8", R.drawable.alcasser, "C.H. ALCASSER", "6", "6", "3", "0", "3"),
                Equipo("9", R.drawable.santapola, "SANTA POLA", "6", "7", "3", "0", "4"),
                Equipo("10", R.drawable.marni, "LEVANTE UDBM MARNI B", "5", "7", "2", "1", "4"),
                Equipo("11", R.drawable.eonalicante, "EON HORNEO ALICANTE", "5", "7", "2", "1", "4"),
                Equipo("12", R.drawable.mislata, "CLUB BALONMANO MISLATA B", "4", "7", "2", "0", "5"),
                Equipo("13", R.drawable.maristes, "CB. MARISTES ALGEMESI", "2", "7", "1", "0", "6"),
                Equipo("14", R.drawable.quart, "CBM QUART", "0", "7", "0", "0", "7"),
            )
            setUpReyclerView(equips)
        }
    }

    private fun setUpReyclerView(equips: MutableList<Equipo>) {
        val equipoAdapter = EquiposAdapter(this.requireContext(), equips)
        binding.recView2.adapter = equipoAdapter
        binding.recView2.layoutManager = LinearLayoutManager(this.requireContext(),
            LinearLayoutManager.VERTICAL, false)
        binding.recView2.addItemDecoration(DividerItemDecoration(this.requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //nasin
    }

}