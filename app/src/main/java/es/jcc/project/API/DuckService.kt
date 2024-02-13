package es.jcc.project.API

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
interface DuckService {

    @GET("random")
    fun getRandomDuck(): Call<DuckResponse>
}