package jp.co.cyberagent.dojo2019.Repository

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import jp.co.cyberagent.dojo2019.Api.GithubService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GithubRepoRepository {

    fun createGithubService(githubID: String): GithubService {
        val url = "https://api.github.com/users/" + githubID + "/" // https://api.github.com/users/hmarf/repos
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