package es.jcc.project.API

import retrofit2.Response
import retrofit2.http.GET
interface ArtsyService {

    @GET("artworks")
    suspend fun getArtworks(): Response<EmbeddedData>
}