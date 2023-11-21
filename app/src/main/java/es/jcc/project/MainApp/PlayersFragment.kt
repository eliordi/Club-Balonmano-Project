package es.jcc.project.MainApp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import es.jcc.project.Login.WelcomeFragment
import es.jcc.project.R
import java.lang.Exception


class PlayersFragment : Fragment(), View.OnClickListener {

    private var mListener: PlayersFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is PlayersFragmentListener){
            mListener = context
        }else{
            throw Exception("The activity must implement the interface PlayersFragmentListener")
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_players, container, false)

        val primera: ImageButton = view.findViewById(R.id.primeraButton)
        val segona: ImageButton = view.findViewById(R.id.segonaButton)

        primera.setOnClickListener(this)
        segona.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.primeraButton -> mListener?.onPrimeraButtonClicked()
            R.id.segonaButton -> mListener?.onSegonaButtonClicked()
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface PlayersFragmentListener{

        fun onPrimeraButtonClicked()
        fun onSegonaButtonClicked()
    }

}