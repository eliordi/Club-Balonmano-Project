package es.jcc.project.MainApp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageButton
import es.jcc.project.Login.WelcomeFragment
import es.jcc.project.R
import es.jcc.project.databinding.ActivityAppBinding
import es.jcc.project.databinding.FragmentPlayersBinding
import java.lang.Exception


class PlayersFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentPlayersBinding

    var seleccion: String = ""
    //var jugadors: MutableList<Jugador> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_players, container, false)

        binding = FragmentPlayersBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}