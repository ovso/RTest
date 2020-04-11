package io.github.ovso.rtest.view.ui.area_b.tab_b

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.github.ovso.rtest.data.datasource.BStargazersDataSourceFactory
import io.github.ovso.rtest.data.network.model.BStargazer
import io.github.ovso.rtest.data.network.model.ShareModel
import io.github.ovso.rtest.exts.plusAssign
import io.github.ovso.rtest.utils.rx.RxBus
import io.github.ovso.rtest.utils.rx.SchedulerProvider
import io.github.ovso.rtest.view.base.DisposableViewModel

class BTabViewModel : DisposableViewModel() {

  private var sourceFactory: BStargazersDataSourceFactory =
    BStargazersDataSourceFactory(compositeDisposable)
  private var bStargazerList: LiveData<PagedList<BStargazer>>? = null

  init {
    observe()
  }

  private fun observe() {
    compositeDisposable += RxBus
      .toObservable()
      .observeOn(SchedulerProvider.ui())
      .subscribe {
        if (it is ShareModel.LoadInitial && ShareModel.bStargazers.count() > 0) {
          bStargazerList?.let {
            val config = PagedList.Config.Builder()
              .setPageSize(30)
              .setInitialLoadSizeHint(30)
              .setEnablePlaceholders(false)
              .build()
            bStargazerList = LivePagedListBuilder(sourceFactory, config).build()
          }
        }
      }
  }

  fun getItems(): LiveData<PagedList<BStargazer>>? = bStargazerList
}
