package es.jcc.project.Managers

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import es.jcc.project.Classes.Mensaje
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import kotlinx.coroutines.flow.Flow

class FirestoreManager() {

    private val firestore by lazy { FirebaseFirestore.getInstance() }
    private val auth = AuthManager()
    private val email = auth.getCurrentUser()?.email.toString()

    suspend fun addMensaje(mensaje: Mensaje): Boolean {
        return try {
            mensaje.email = email
            firestore.collection(CHAT_COLLECTION).add(mensaje).await()
            true
        }catch (e: Exception){
            false
        }
    }

    suspend fun deleteMensaje(mensajeId: String): Boolean {
        return try {
            val mensajeRef = firestore.collection(CHAT_COLLECTION).document(mensajeId)
            mensajeRef.delete().await()
            true
        }catch (e: Exception){
            false
        }
    }

    suspend fun getMensajes(): Flow<List<Mensaje>> = callbackFlow {
        var chatCollection: CollectionReference? = null
        try {
            chatCollection = FirebaseFirestore.getInstance()
                .collection(CHAT_COLLECTION)

            val subscription = chatCollection?.addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    val mensajes = mutableListOf<Mensaje>()
                    snapshot.forEach {
                        mensajes.add(
                            Mensaje(
                                id = it.id,
                                email = it.get(MENSAJE_EMAIL).toString(),
                                text = it.get(MENSAJE_TEXT).toString()
                            )
                        )
                    }
                    trySend(mensajes)
                }
            }
            awaitClose { subscription?.remove() }
        } catch (e: Throwable) {
            close(e)
        }
    }

    companion object{
        const val CHAT_COLLECTION = "chat"
        const val MENSAJE_EMAIL = "email"
        const val MENSAJE_TEXT = "text"
    }
}