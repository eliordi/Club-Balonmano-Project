package es.jcc.project.MainApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.jcc.project.API.DuckResponse
import es.jcc.project.API.DuckService
import es.jcc.project.API.RetrofitObject
import es.jcc.project.Adapters.DucksAdapter
import es.jcc.project.R
import es.jcc.project.databinding.FragmentRetrofitBinding
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitFragment : Fragment() {

    private lateinit var binding: FragmentRetrofitBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DucksAdapter
    private val ducks: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val view = inflater.inflate(R.layout.fragment_retrofit, container, false)

        binding = FragmentRetrofitBinding.inflate(layoutInflater)
        setRecyclerView()
        binding.randomButton.setOnClickListener{
            getRandomDuck()
        }

        return binding.root
    }
    private fun setRecyclerView(){
        adapter = DucksAdapter(this.requireContext(), ducks)
        binding.recViewRetrofit.adapter = adapter
        binding.recViewRetrofit.layoutManager = GridLayoutManager(context, 2)
    }

    private fun getRandomDuck() {
        val service = RetrofitObject.getInstance().create(DuckService::class.java)
        service.getRandomDuck().enqueue(object : Callback<DuckResponse> {
            override fun onResponse(call: Call<DuckResponse>, response: Response<DuckResponse>) {
                if (response.isSuccessful) {
                    val imageUrl = response.body()?.imageUrl
                    if (!imageUrl.isNullOrEmpty()) {
                        updateDucks(imageUrl)
                    } else {
                        Toast.makeText(requireContext(), "Empty response", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed to get duck", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DuckResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Network error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateDucks(imageUrls: String) {
        ducks.add(imageUrls)
        adapter.notifyDataSetChanged()
    }

}

