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
import androidx.lifecycle.lifecycleScope
import es.jcc.project.AuthManager
import es.jcc.project.Dialogs.MyDialog
import es.jcc.project.R
import es.jcc.project.databinding.FragmentSignupBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class SignupFragment : Fragment(){

    private lateinit var mListener: SignupFragmentListener
    private lateinit var binding: FragmentSignupBinding
    private lateinit var authManager: AuthManager
    

    override fun onAttach(context: Context) {
        super.onAttach(context)

        authManager = AuthManager()
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
        
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        
        binding.signupButton2.setOnClickListener { 
            val email = binding.emailEditText2.text.toString()
            val pass = binding.passEditText2.text.toString()
            if (!email.isNullOrBlank() && !pass.isNullOrBlank()){
                lifecycleScope.launch(Dispatchers.IO){
                    val userLogged = authManager.signUp(email, pass)
                    withContext(Dispatchers.Main){
                        if (userLogged != null){
                            Toast.makeText(requireContext(), userLogged.email, Toast.LENGTH_SHORT).show()
                            mListener.onSignupButton2Clicked()
                        }else{
                            MyDialog().show(requireActivity().supportFragmentManager, "REGISTER")
                        }
                    }
                }
            }
        }

        binding.backButton2.setOnClickListener {
            mListener.onBackButton2Clicked()
        }
        

        return binding.root
    }

    interface SignupFragmentListener{
        fun onBackButton2Clicked()
        fun onSignupButton2Clicked()
    }


}