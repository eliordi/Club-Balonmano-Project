package es.jcc.project.API

import com.google.gson.annotations.SerializedName

data class EmbeddedData(
    @SerializedName("artworks") val artworks: List<Artwork>
)
data class Artwork(
    @SerializedName("title") val title: String,
    @SerializedName("image") val imageUrl: ImageUrl,
    @SerializedName("artists") val artists: List<Artist>
)

data class ImageUrl(
    @SerializedName("url") val url: String
)

data class Artist(
    @SerializedName("name") val name: String
)

