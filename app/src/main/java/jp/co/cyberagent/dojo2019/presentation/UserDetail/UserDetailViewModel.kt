package jp.co.cyberagent.dojo2019.presentation.UserDetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.Entity.GithubRepository
import jp.co.cyberagent.dojo2019.Entity.User
import jp.co.cyberagent.dojo2019.Repository.GithubRepoRepository
import jp.co.cyberagent.dojo2019.Repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application)
    private val githubRepoRepository = GithubRepoRepository()

    fun findUserBy(uid: Int): LiveData<User> {
        return userRepository.fetchUserBy(uid)
    }

    fun fetchReposBy(githubID: String, callback: (List<GithubRepository>) -> Unit) {
        val githubService = githubRepoRepository.createGithubService(githubID)
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
            override fun onFailure(call: Call<List<GithubRepository>>?, t: Throwable?) {
            }
        })
    }
}