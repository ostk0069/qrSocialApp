package jp.co.cyberagent.dojo2019.Entity

import com.squareup.moshi.Json

data class GithubRepository(
    @Json(name = "id") val id: Int, // ex) 198217772
    @Json(name = "full_name") val name: String, // ex) hmarf/Django_softwore
    @Json(name = "html_url") val url: String,  // ex) https://github.com/hmarf/Django_softwore
    @Json(name = "language") val language: String, // ex) Python
    @Json(name = "stargazers_count") val star: Int // ex) 0
)