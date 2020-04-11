package io.github.ovso.rtest.view.ui.area_b.tab_b

import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.model.ShareModel
import io.github.ovso.rtest.exts.plusAssign
import io.github.ovso.rtest.utils.rx.RxBus
import io.github.ovso.rtest.utils.rx.SchedulerProvider
import io.github.ovso.rtest.view.base.DisposableViewModel

class BTabViewModel : DisposableViewModel() {
  private val repository by lazy { GithubRepository() }

  init {
    compositeDisposable += RxBus
      .toObservable()
      .observeOn(SchedulerProvider.ui())
      .subscribe {
        if (it is ShareModel.LoadInitial) {
          print(Thread.currentThread().name)
          ShareModel.reposSparseArray
          ShareModel.stargazersSparseArray
        }
      }
  }
}
