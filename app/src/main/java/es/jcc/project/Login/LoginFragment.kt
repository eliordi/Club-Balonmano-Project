package es.jcc.project.Login

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import es.jcc.project.Dialogs.MyDialog
import es.jcc.project.Dialogs.MyDialog2
import es.jcc.project.R
import java.lang.Exception

class LoginFragment : Fragment(), View.OnClickListener {

    private var mListener: LoginFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is LoginFragmentListener){
            mListener = context
        }else{
            throw Exception("The activity must implement the interface MenuFragmentListener")
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val back: Button = view.findViewById(R.id.backButton)
        val login2: Button = view.findViewById(R.id.loginButton2)

        val userET: EditText = view.findViewById(R.id.userEditText)
        val passET: EditText = view.findViewById(R.id.passEditText)



        back.setOnClickListener(this)
        login2.setOnClickListener{
            val user = userET.text.toString()
            val pass = passET.text.toString()

            if (user == "admin" && pass == "1234"){
                //Toast.makeText(context, "Perfect", Toast.LENGTH_SHORT).show()
                mListener?.onLoginButton2Clicked()
            }else{
                MyDialog2().show(this.childFragmentManager, "LOGIN")
            }
        }

        return view
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.backButton -> mListener?.onBackButtonClicked()
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface LoginFragmentListener{
        fun onBackButtonClicked()
        fun onLoginButton2Clicked()
    }

}