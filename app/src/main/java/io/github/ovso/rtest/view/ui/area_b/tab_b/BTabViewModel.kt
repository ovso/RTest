package io.github.ovso.rtest.view.ui.area_b.tab_b

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.github.ovso.rtest.App
import io.github.ovso.rtest.data.db.model.StargazerEntity
import io.github.ovso.rtest.data.network.model.BStargazer
import io.github.ovso.rtest.exts.clearDb
import io.github.ovso.rtest.exts.plusAssign
import io.github.ovso.rtest.utils.rx.SchedulerProvider
import io.github.ovso.rtest.view.base.DisposableViewModel
import io.reactivex.rxjava3.core.Single

class BTabViewModel(private val owner: LifecycleOwner) : DisposableViewModel() {

  init {
    observe()
  }

  private fun observe() {
    App.appDb.stargazers().stargazers().observe(owner, Observer {
      compositeDisposable += Single.fromCallable { toBStargazers(it) }
        .subscribeOn(SchedulerProvider.io())
        .observeOn(SchedulerProvider.ui())
        .subscribe({
          bStargazerList.value = it
        }, {})

    })
  }

  private fun toBStargazers(stargazerEntities: List<StargazerEntity>): List<BStargazer> {
    val listOf = mutableListOf<BStargazer>()
    stargazerEntities.forEach { entity ->
      listOf.add(
        BStargazer(
          id = entity.id,
          avatarUrl = entity.avatarUrl,
          login = entity.login,
          repoName = entity.repoName
        )
      )
    }
    return listOf
  }

  private val bStargazerList = MutableLiveData<List<BStargazer>>()
  fun getItems(): LiveData<List<BStargazer>> = bStargazerList

  override fun onCleared() {
    super.onCleared()
    clearDb()
  }
}
