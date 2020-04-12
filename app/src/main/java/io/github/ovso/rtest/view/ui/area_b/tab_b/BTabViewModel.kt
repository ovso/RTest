package io.github.ovso.rtest.view.ui.area_b.tab_b

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import io.github.ovso.rtest.App
import io.github.ovso.rtest.data.datasource.BStargazersDataSourceFactory
import io.github.ovso.rtest.data.db.model.RepoEntity
import io.github.ovso.rtest.data.network.model.BStargazer
import io.github.ovso.rtest.view.base.DisposableViewModel
import timber.log.Timber

class BTabViewModel : DisposableViewModel() {

  private var sourceFactory: BStargazersDataSourceFactory =
    BStargazersDataSourceFactory(compositeDisposable)
  var bStargazerList: LiveData<PagedList<BStargazer>>? = null

  init {
    observe()
  }

  private fun observe() {
//    App.appDb.repos().repos2().observeForever(Observer {
//      Timber.d("observe = ${it.count()}")
//      println("ThreadName = ${Thread.currentThread().name}")
//    })
  }

/*
  private fun observe() {
    compositeDisposable += RxBus
      .toObservable()
      .observeOn(SchedulerProvider.ui())
      .subscribe {
        if (it is ShareModel.LoadInitial && ShareModel.bStargazers.count() > 0) {
          val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setInitialLoadSizeHint(30)
            .setEnablePlaceholders(false)
            .build()
          bStargazerList = LivePagedListBuilder(sourceFactory, config).build()
        }
      }
  }
*/

  fun getItems(): LiveData<PagedList<BStargazer>>? = bStargazerList
}
