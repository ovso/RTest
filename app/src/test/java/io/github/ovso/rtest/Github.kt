@file:Suppress("NonAsciiCharacters")

package io.github.ovso.rtest

import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.User
import io.github.ovso.rtest.data.network.model.Repo
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test

class Github {
  private val repo by lazy { GithubRepository() }

  @Test
  fun `깃허브 사용자 저장소 목록 가져오기`() {

    fun onSuccess(repos: List<Repo>) {
      println(repos.count())
    }

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    repo.api().userRepos(User.name, 1, 5)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)
  }


  object SchedulerProvider {
    fun io(): Scheduler = Schedulers.trampoline()
    fun ui(): Scheduler = Schedulers.trampoline()
  }
}
