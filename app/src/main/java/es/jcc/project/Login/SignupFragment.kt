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
import es.jcc.project.R
import java.lang.Exception

class SignupFragment : Fragment(), View.OnClickListener {

    private var mListener: SignupFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SignupFragmentListener){
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
        val view = inflater.inflate(R.layout.fragment_signup, container, false)

        val back2: Button = view.findViewById(R.id.backButton2)
        val signup2: Button = view.findViewById(R.id.signupButton2)

        val nameET: EditText = view.findViewById(R.id.nameEditText)
        val userET: EditText = view.findViewById(R.id.userEditText2)
        val passET: EditText = view.findViewById(R.id.passEditText2)

        back2.setOnClickListener(this)
        signup2.setOnClickListener{
            val name = nameET.text.toString()
            val user = userET.text.toString()
            val pass = passET.text.toString()

            if (name.isEmpty() || user.isEmpty() || pass.isEmpty()){
                MyDialog().show(this.childFragmentManager, "REGISTER")
            }else{
                mListener?.onSignupButton2Clicked()

            }
        }

        return view
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.backButton2 -> mListener?.onBackButton2Clicked()
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface SignupFragmentListener{

        fun onBackButton2Clicked()
        fun onSignupButton2Clicked()
    }


}