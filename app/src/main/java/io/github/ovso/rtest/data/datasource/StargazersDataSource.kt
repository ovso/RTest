package io.github.ovso.rtest.data.datasource

import androidx.paging.PageKeyedDataSource
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.User
import io.github.ovso.rtest.data.network.model.ShareModel
import io.github.ovso.rtest.data.network.model.Stargazer
import io.github.ovso.rtest.exts.plusAssign
import io.github.ovso.rtest.utils.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable

class StargazersDataSource(
  private val compositeDisposable: CompositeDisposable,
  private val repository: GithubRepository,
  private val repoName: String,
  private val repoAvatarUrl: String
) : PageKeyedDataSource<Int, Stargazer>() {
  override fun loadInitial(
    params: LoadInitialParams<Int>,
    callback: LoadInitialCallback<Int, Stargazer>
  ) {
    val pageKey = 1
    fun onSuccess(stargazers: List<Stargazer>) {
      callback.onResult(stargazers, pageKey, pageKey + 1)
    }

    compositeDisposable += repository.api().stargazers(User.name, repoName, pageKey, 30)
      .map { ShareModel.addStargazers(it, repoName, repoName); it }
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess) { println(it) }
  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Stargazer>) {

    fun onSuccess(stargazers: List<Stargazer>) {
      callback.onResult(stargazers, params.key + 1)
    }

    compositeDisposable += repository.api().stargazers(User.name, repoName, params.key, 50)
      .map { ShareModel.addStargazers(it, repoName, repoAvatarUrl); it }
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess) { println(it) }
  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Stargazer>) {}
}
