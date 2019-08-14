package jp.co.cyberagent.dojo2019.Repository

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import jp.co.cyberagent.dojo2019.Database.GithubService
import jp.co.cyberagent.dojo2019.Entity.GithubRepository
import retrofit2.Call
import retrofit2.Callback
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GithubRepoRepository {

    fun fetchRepos(githubID: String, callback: (List<GithubRepository>) -> Unit) {
        val githubService = createGithubService(githubID)
        githubService.fetchRepos().enqueue(object : Callback<List<GithubRepository>> {

            override fun onResponse(
                call: Call<List<GithubRepository>>?,
                response: Response<List<GithubRepository>>?
            ) {
                response?.let {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback(it)
                        }
                    }
                }
            }
            // FIXME: add error handling feature
            override fun onFailure(call: Call<List<GithubRepository>>?, t: Throwable?) {}
        })
    }

    private fun createGithubService(githubID: String): GithubService {
        val url = "https://api.github.com/users/" + githubID + "/repos" // https://api.github.com/users/hmarf/repos
        val okHttpClient = OkHttpClient.Builder().build()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
        return retrofit.create(GithubService::class.java)
    }
}