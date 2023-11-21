package es.jcc.project.Login

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import es.jcc.project.R
import java.lang.Exception


class WelcomeFragment : Fragment(), View.OnClickListener {

    private var mListener: WelcomeFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is WelcomeFragmentListener){
            mListener = context
        }else{
            throw Exception("The activity must implement the interface WelcomeFragmentListener")
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        val login: Button = view.findViewById(R.id.loginButton)
        val signup: Button = view.findViewById(R.id.signupButton)

        login.setOnClickListener(this)
        signup.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.loginButton -> mListener?.onLoginButtonClicked()
            R.id.signupButton -> mListener?.onSignupButtonClicked()
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface WelcomeFragmentListener{

        fun onLoginButtonClicked()
        fun onSignupButtonClicked()
    }


}