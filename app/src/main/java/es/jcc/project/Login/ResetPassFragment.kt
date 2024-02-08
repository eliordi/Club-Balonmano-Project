package es.jcc.project.Login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import es.jcc.project.AuthManager
import es.jcc.project.R
import es.jcc.project.databinding.FragmentResetPassBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ResetPassFragment : Fragment() {
    private lateinit var binding: FragmentResetPassBinding
    private lateinit var mListener: ResetPassFragmentListener
    private lateinit var authManager: AuthManager

    override fun onAttach(context: Context) {
        super.onAttach(context)

        authManager = AuthManager()
        if(context is ResetPassFragmentListener){
            mListener = context
        }else{
            throw Exception("Your fragment or activity must implement the interface ResetPassFragmentListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentResetPassBinding.inflate(inflater, container, false)

        binding.sendButton.setOnClickListener{
            val email = binding.editTextEmail.text.toString()
            if (!email.isNullOrBlank()){
                lifecycleScope.launch(Dispatchers.IO){
                    authManager.rememberPassword(email)
                    withContext(Dispatchers.Main){
                        Toast.makeText(requireContext(), "Email enviado correctamente", Toast.LENGTH_SHORT).show()
                        mListener.onSendButtonClicked()
                    }
                }
            }
        }

        binding.backButton3.setOnClickListener{
            mListener.onBackButton3Clicked()
        }

        return binding.root
    }


    interface ResetPassFragmentListener{
        fun onSendButtonClicked()
        fun onBackButton3Clicked()
    }
}