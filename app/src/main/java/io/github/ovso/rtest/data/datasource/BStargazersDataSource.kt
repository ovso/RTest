package io.github.ovso.rtest.data.datasource

import androidx.paging.PositionalDataSource
import io.github.ovso.rtest.data.network.model.BStargazer
import io.reactivex.rxjava3.disposables.CompositeDisposable

class BStargazersDataSource(
  private val compositeDisposable: CompositeDisposable
) : PositionalDataSource<BStargazer>() {

  override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<BStargazer>) {
    val position = 0
//    callback.onResult(subList, position + (params.pageSize - 1))
  }

  override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<BStargazer>) {
//    callback.onResult(subList)
  }

}
