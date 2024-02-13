package es.jcc.project.Managers

import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.type.DateTime
import es.jcc.project.Classes.Mensaje
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import kotlinx.coroutines.flow.Flow

import java.util.Date

class FirestoreManager() {

    private val firestore by lazy { FirebaseFirestore.getInstance() }
    suspend fun addMensaje(mensaje: Mensaje): Boolean {
        return try {
            mensaje.date = Timestamp.now()
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

            val subscription = chatCollection?.orderBy("date")?.addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    val mensajes = mutableListOf<Mensaje>()
                    snapshot.forEach {
                        mensajes.add(
                            Mensaje(
                                id = it.id,
                                email = it.get(MENSAJE_EMAIL).toString(),
                                text = it.get(MENSAJE_TEXT).toString(),
                                date = Timestamp.now()
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