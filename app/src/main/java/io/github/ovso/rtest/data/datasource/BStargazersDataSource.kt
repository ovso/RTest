package io.github.ovso.rtest.data.datasource

import androidx.paging.LivePagedListBuilder
import androidx.paging.PositionalDataSource
import io.github.ovso.rtest.App
import io.github.ovso.rtest.data.network.model.BStargazer
import io.github.ovso.rtest.exts.plusAssign
import io.github.ovso.rtest.utils.rx.SchedulerProvider
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber

class BStargazersDataSource(
  private val compositeDisposable: CompositeDisposable
) : PositionalDataSource<BStargazer>() {

  override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<BStargazer>) {
    val position = 0
    Timber.d("loadInit")
    compositeDisposable += Single.fromCallable { "" }
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe({
        App.appDb.stargazers().stargazers().observeForever {
          val listOf = mutableListOf<BStargazer>()
          it.forEach { entity ->
            listOf.add(
              BStargazer(
                id = entity.id,
                avatarUrl = entity.avatarUrl,
                login = entity.login,
                repoName = ""
              )
            )
          }
          callback.onResult(listOf, 0)
        }
      }, {})

  }

  override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<BStargazer>) {
//    callback.onResult(subList)
    Timber.d("loadRange")
  }

}
