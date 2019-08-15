package jp.co.cyberagent.dojo2019.Api

import jp.co.cyberagent.dojo2019.Entity.GithubRepository
import retrofit2.Call
import retrofit2.http.GET

interface GithubService {

    @GET("repos")
    fun fetchRepos(): Call<List<GithubRepository>>
}