package es.jcc.project.MainApp

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import es.jcc.project.R
import es.jcc.project.databinding.FragmentMultimediaBinding


class MultimediaFragment : Fragment() {

    private lateinit var binding: FragmentMultimediaBinding
    private lateinit var editTextVideo: EditText
    private lateinit var btnSearch: Button
    private lateinit var videoView: VideoView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMultimediaBinding.inflate(layoutInflater)

        editTextVideo = binding.editTextVideo
        btnSearch = binding.searchButton
        videoView = binding.videoView

        val prefs = requireContext().getSharedPreferences("es.jcc.project_preferences", Context.MODE_PRIVATE)
        val vibrate = prefs.getBoolean("vibrate", false)

        btnSearch.setOnClickListener {
            if (vibrate){
                vibrateDevice()
            }
            prepareVideo()

        }

        return binding.root
    }

    private fun prepareVideo(){

        val nomVideo = editTextVideo.text.toString().trim()
        val idVideo = resources.getIdentifier(nomVideo, "raw", "es.jcc.project")

        if (idVideo != 0) {
            binding.videoView.setVideoURI(
                Uri.parse("android.resource://es.jcc.project/$idVideo")
            )
            val mediaController = MediaController(requireContext())
            mediaController.setAnchorView(binding.videoView)
            mediaController.setMediaPlayer(binding.videoView)
            binding.videoView.setMediaController(mediaController)

            videoView.start()
        } else {
            Toast.makeText(requireContext(), "Video not found", Toast.LENGTH_SHORT).show()
        }
    }

    fun vibrateDevice() {
        var vibrator: Vibrator
        if (Build.VERSION.SDK_INT>=31){
            val vibratorManager = activity?.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibrator = vibratorManager.defaultVibrator
        }
        else {
            @Suppress("DEPRECATION")
            vibrator = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        val mvibratePattern = longArrayOf(0, 400, 200, 400)
        if (Build.VERSION.SDK_INT >= 26){
            vibrator.vibrate(VibrationEffect.createWaveform(mvibratePattern, -1))
        }
        else{
            @Suppress("DEPRECATION")
            vibrator.vibrate(mvibratePattern, -1)
        }
    }

}