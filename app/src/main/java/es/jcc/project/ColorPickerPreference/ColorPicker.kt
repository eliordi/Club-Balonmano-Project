package es.jcc.project.ColorPickerPreference

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.preference.Preference
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener

class ColorPicker(context: Context, attrs: AttributeSet) : Preference(context, attrs) {

    private var selectedColor: Int = Color.BLACK

    init {
        setOnPreferenceClickListener {
            showColorPickerDialog()
            true
        }
    }

    private fun showColorPickerDialog() {
        val builder = ColorPickerDialog.Builder(context)
            .setTitle("Pick a color")
            .setPreferenceName("MyColorPickerPreference")
            .setPositiveButton("Confirm", ColorEnvelopeListener { envelope, _ ->
                selectedColor = envelope.color
                persistInt(selectedColor)
            })
            .setNegativeButton("Cancel") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }

        builder.show()
    }

    override fun getSummary(): CharSequence {
        return String.format("#%06X", 0xFFFFFF and selectedColor)
    }

    override fun onSetInitialValue(defaultValue: Any?) {
        super.onSetInitialValue(defaultValue)
        selectedColor = getPersistedInt(Color.BLACK)
    }
}