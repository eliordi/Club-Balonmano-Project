package es.jcc.project.MainApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import es.jcc.project.Adapters.MensajesAdapter
import es.jcc.project.Managers.AuthManager
import es.jcc.project.Classes.Mensaje
import es.jcc.project.Dialogs.DeleteDialog
import es.jcc.project.Managers.FirestoreManager
import es.jcc.project.R
import es.jcc.project.databinding.FragmentChatBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.Date


class ChatFragment : Fragment(), MensajesAdapter.OnMensajeCLickedListener{

    private lateinit var binding: FragmentChatBinding
    private lateinit var mensajes: MutableList<Mensaje>
    private lateinit var mAdapter: MensajesAdapter
    private val firestoreManager: FirestoreManager by lazy { FirestoreManager() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater, container, false)

        binding.sendButton.setOnClickListener {
            var text = binding.etMensaje.text.toString()
            var email = AuthManager().getCurrentUser()?.email.toString()
            createNewMensaje(email, text)
            binding.etMensaje.setText("")
        }

        setUpRecyclerView()

        return binding.root
    }

    private fun setUpRecyclerView(){
        mensajes = mutableListOf()
        mAdapter = MensajesAdapter(this.requireContext(), mensajes, this)
        binding.recViewChat.adapter = mAdapter
        binding.recViewChat.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)


        lifecycleScope.launch (Dispatchers.IO){
            firestoreManager.getMensajes()
                .collect{ mensajesUpdated ->
                    mensajes.clear()
                    mensajes.addAll(mensajesUpdated)
                    withContext(Dispatchers.Main){
                        mAdapter.notifyDataSetChanged()
                    }
                }
        }
    }

    private fun createNewMensaje(email: String, text: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val newMensaje = Mensaje(email = email, text = text)
                firestoreManager.addMensaje(newMensaje)

            } catch (e: Exception){
                e.toString()
            }
        }
    }

    private fun deleteMensaje(docId: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                firestoreManager.deleteMensaje(docId)

            }catch (e: Exception){
                e.toString()
            }
        }
    }

    override fun onMensajeLongClicked(mensaje: Mensaje): Boolean {
        val deleteDialogFunction = { docId: String ->
            deleteMensaje(docId)
        }
        DeleteDialog(mensaje, deleteDialogFunction, this.requireContext())
            .show(childFragmentManager, "ELIMINAR MENSAJE")

        return true
    }


}