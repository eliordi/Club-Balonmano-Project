package es.jcc.project.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import es.jcc.project.Classes.Mensaje

class DeleteDialog(private val mensaje: Mensaje,
                   private val deleteFunction: (documentId: String) -> Unit,
                   private val context: Context): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Eliminar mensaje")

        builder.setPositiveButton("Eliminar"){ _, _ ->
            deleteFunction(mensaje.id!!)
        }
        builder.setNegativeButton("Cancelar"){ dialog, _ -> dialog.dismiss() }
        return builder.create()
    }

}