package io.github.ovso.rtest.view.ui.area_b.tab_a

import androidx.lifecycle.ViewModel
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.User
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.utils.rx.SchedulerProvider

class ATabViewModel : ViewModel() {
  private val repo by lazy { GithubRepository() }

  init {
    reqRepos()
  }

  private fun reqRepos() {
    fun onSuccess(repos: List<Repo>) {
      println(repos.count())
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
}
