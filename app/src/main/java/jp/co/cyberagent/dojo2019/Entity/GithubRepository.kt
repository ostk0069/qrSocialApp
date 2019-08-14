package jp.co.cyberagent.dojo2019.Entity

import com.squareup.moshi.Json

data class GithubRepository(
    @Json(name = "id") val id: Int, // 198217772
    @Json(name = "name") val name: String, // hmarf/Django_softwore
    @Json(name = "url") val url: String,  // https://github.com/hmarf/Django_softwore
    @Json(name = "star") val star: String // 0
)