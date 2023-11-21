package es.jcc.project.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import es.jcc.project.R

class MyDialog : DialogFragment() {

    private lateinit var mListener: MyDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder
                .setView(inflater.inflate(R.layout.signup_dialog, null))
                .setPositiveButton("Ok"){ dialog, id ->
                    mListener.onDialogPositiveClick()
                }
            builder.create()
        } ?: throw java.lang.IllegalStateException("Activity cannot be null")
    }

    interface MyDialogListener{
        fun onDialogPositiveClick()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MyDialogListener){
            mListener = context
        }else{
            throw Exception("Your fragment or activity must implement the interface MyDialogListener")
        }
    }
}