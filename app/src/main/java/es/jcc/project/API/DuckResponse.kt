package es.jcc.project.API

import com.google.gson.annotations.SerializedName

data class DuckResponse(
    @SerializedName("url") val imageUrl: String,
    @SerializedName("message") val message: String
)


