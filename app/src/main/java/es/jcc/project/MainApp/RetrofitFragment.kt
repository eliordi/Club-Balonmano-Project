package es.jcc.project.MainApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import es.jcc.project.API.ArtsyService
import es.jcc.project.API.Artwork
import es.jcc.project.API.RetrofitObject
import es.jcc.project.Adapters.ArtAdapter
import es.jcc.project.R
import es.jcc.project.databinding.FragmentRetrofitBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RetrofitFragment : Fragment() {

    private lateinit var binding: FragmentRetrofitBinding
    private lateinit var mAdapter: ArtAdapter
    private val artworks: MutableList<Artwork> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRetrofitBinding.inflate(inflater, container, false)

        setUpRecycler()
        getArtworks()


        return binding.root
    }

    private fun getArtworks() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val call = RetrofitObject.getInstance().create(ArtsyService::class.java).getArtworks()
                val response = call.body()
                withContext(Dispatchers.Main) {
                    if (response != null) {
                        updateArtworks(response.artworks)
                    } else {
                        Toast.makeText(requireContext(), "Response is null", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateArtworks(artworksList: List<Artwork>) {
        artworks.clear()
        artworks.addAll(artworksList)
        mAdapter.notifyDataSetChanged()
    }

    private fun setUpRecycler() {
        mAdapter = ArtAdapter(requireContext(), artworks)
        binding.recViewRetrofit.adapter = mAdapter
        binding.recViewRetrofit.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}
