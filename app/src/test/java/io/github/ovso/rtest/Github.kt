@file:Suppress("NonAsciiCharacters")

package io.github.ovso.rtest

import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.User
import io.github.ovso.rtest.data.network.model.ItemModel
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.data.network.model.Stargazer
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test

class Github {
  private val repository by lazy { GithubRepository() }

  @Test
  fun `깃허브 사용자 저장소 목록 가져오기`() {

    fun onSuccess(items: List<Repo>) {
      println(items.count())
    }

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    repository.api().userRepos(User.name, 1, 3)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)
  }

  @Test
  fun `깃허브 저장소에 스타를 준 stargazer 목록`() {

    fun onSuccess(items: List<Stargazer>) {
      println(items.size)
    }

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    repository.api().stargazers(User.name, "android-PermissionRequest", 1, 3)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)
  }

  @Test
  fun `깃허브 사용자 저장소 목록과 목록별 스타게이저 정보 가져오기`() {

    fun onSuccess(items: List<ItemModel>) {
      println(items.toString())
    }

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    fun reqStargazers(repo: Repo): Single<ItemModel> {
      return repository.api()
        .stargazers(user = User.name, repo = repo.name, page = 1, per_page = 3)
        .map { ItemModel(repo, it) }
    }
    repository.api().userRepos(user = User.name, page = 1, per_page = 3)
      .flatMapObservable { Observable.fromIterable(it) }
      .flatMapSingle { reqStargazers(it) }
      .toList()
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)
  }

  object SchedulerProvider {
    fun io(): Scheduler = Schedulers.trampoline()
    fun ui(): Scheduler = Schedulers.trampoline()
  }
}
