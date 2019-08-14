package jp.co.cyberagent.dojo2019.presentation.UserDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.Entity.GithubRepository
import jp.co.cyberagent.dojo2019.Entity.User
import jp.co.cyberagent.dojo2019.Repository.GithubRepoRepository
import jp.co.cyberagent.dojo2019.Repository.UserRepository

class UserDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application)
    private val githubRepoRepository = GithubRepoRepository()

    fun findUserBy(uid: Int): LiveData<User> {
        return userRepository.fetchUserBy(uid)
    }

    fun fetchGithubReposBy(githubID: String): MutableList<GithubRepository> {
        var repoList: MutableList<GithubRepository> = mutableListOf()
        githubRepoRepository.fetchRepos(githubID, {
                repos -> repoList.addAll(repos)
        })
        return repoList
    }
}