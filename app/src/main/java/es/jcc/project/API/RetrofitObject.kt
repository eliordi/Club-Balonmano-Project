package es.jcc.project.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitObject {

    companion object {
        private var instance: Retrofit? = null
        fun getInstance(): Retrofit {
            synchronized(this) {
                if (instance == null) {
                    instance = Retrofit.Builder()
                        .baseUrl("https://api.artsy.net/api/") // Cambia la URL base según tu API de arte
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return instance!!
            }
        }
    }
}
