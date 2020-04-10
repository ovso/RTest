package io.github.ovso.rtest.view.ui.area_b.tab_a

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.User
import io.github.ovso.rtest.data.network.model.RepoResponse
import io.github.ovso.rtest.utils.rx.SchedulerProvider

class ATabViewModel : ViewModel() {
  private val repo by lazy { GithubRepository() }
  private val items = MutableLiveData<List<RepoResponse>>()

  init {
    reqRepos()
  }

  private fun reqRepos() {
    fun onSuccess(repoResponses: List<RepoResponse>) {
      println(repoResponses.count())
      items.value = repoResponses
    }

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    repo.api()
      .userRepos(User.name, 1, 5)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)
  }

  fun getItems():LiveData<List<RepoResponse>> = items
}
