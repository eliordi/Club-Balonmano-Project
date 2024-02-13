package es.jcc.project.Login

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import es.jcc.project.Managers.AuthManager
import es.jcc.project.Dialogs.MyDialog2
import es.jcc.project.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoginFragment : Fragment(){

    private lateinit var binding: FragmentLoginBinding
    private lateinit var mListener: LoginFragmentListener
    private lateinit var authManager: AuthManager

    override fun onAttach(context: Context) {
        super.onAttach(context)

        authManager = AuthManager()
        if (context is LoginFragmentListener){
            mListener = context
        }else{
            throw Exception("The activity must implement the interface LoginFragmentListener")
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.loginButton2.setOnClickListener{
            val email = binding.userEditText.text.toString()
            val pass = binding.passEditText.text.toString()
            if (!email.isNullOrBlank() && !pass.isNullOrBlank()){
                lifecycleScope.launch(Dispatchers.IO) {
                    val userLogged = authManager.login(email, pass)
                    withContext(Dispatchers.Main){
                        if (userLogged != null){
                            Toast.makeText(requireContext(), userLogged.email, Toast.LENGTH_SHORT).show()
                            mListener.onLoginButton2Clicked()
                        }else{
                            MyDialog2().show(requireActivity().supportFragmentManager, "LOGIN")
                            //Toast.makeText(requireContext(), "Bad credentials", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        binding.backButton.setOnClickListener{
            mListener.onBackButtonClicked()
        }

        binding.resetButton?.setOnClickListener {
            mListener.onResetButtonClicked()
        }

        return binding.root
    }

    interface LoginFragmentListener{
        fun onBackButtonClicked()
        fun onLoginButton2Clicked()
        fun onResetButtonClicked()
    }

}