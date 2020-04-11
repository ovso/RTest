package io.github.ovso.rtest.data.datasource

import androidx.paging.PageKeyedDataSource
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.User
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.data.network.model.ShareModel
import io.github.ovso.rtest.exts.plusAssign
import io.github.ovso.rtest.utils.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ReposDataSource(
  private val compositeDisposable: CompositeDisposable,
  private val repository: GithubRepository
) : PageKeyedDataSource<Int, Repo>() {
  override fun loadInitial(
    params: LoadInitialParams<Int>,
    callback: LoadInitialCallback<Int, Repo>
  ) {

    val pageKey = 0
    fun onSuccess(repos: List<Repo>) {
      callback.onResult(repos, pageKey, pageKey + 1)
      ShareModel.addRepos(repos)
    }

    compositeDisposable += repository.api().userRepos(User.name, pageKey, 30)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess) { println(it) }
  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {

    fun onSuccess(repos: List<Repo>) {
      callback.onResult(repos, params.key + 1)
      ShareModel.addRepos(repos)
    }

    compositeDisposable += repository.api().userRepos(User.name, params.key, 30)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess) { println(it) }
  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {}
}
