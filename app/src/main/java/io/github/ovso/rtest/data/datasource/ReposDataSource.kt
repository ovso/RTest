package io.github.ovso.rtest.data.datasource

import androidx.paging.PageKeyedDataSource
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.User
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.exts.plusAssign
import io.github.ovso.rtest.utils.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.atomic.AtomicInteger

class ReposDataSource(
  private val compositeDisposable: CompositeDisposable,
  private val repository: GithubRepository
) : PageKeyedDataSource<Int, Repo>() {
  private val pageKey = AtomicInteger(1)
  override fun loadInitial(
    params: LoadInitialParams<Int>,
    callback: LoadInitialCallback<Int, Repo>
  ) {

    fun onSuccess(repos: List<Repo>) {
      callback.onResult(repos, pageKey.get(), pageKey.incrementAndGet())
    }

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    compositeDisposable += repository.api().userRepos(User.name, pageKey.get(), 30)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)

  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {

    fun onSuccess(repos: List<Repo>) {
      callback.onResult(repos, pageKey.incrementAndGet())
    }

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    compositeDisposable += repository.api().userRepos(User.name, pageKey.get(), 30)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)


  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {

  }

}
