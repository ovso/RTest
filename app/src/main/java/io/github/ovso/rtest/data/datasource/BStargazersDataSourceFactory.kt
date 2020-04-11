package io.github.ovso.rtest.data.datasource

import androidx.paging.DataSource
import io.github.ovso.rtest.data.network.model.BStargazer
import io.reactivex.rxjava3.disposables.CompositeDisposable

class BStargazersDataSourceFactory(
  private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, BStargazer>() {

  override fun create(): DataSource<Int, BStargazer> {
    return BStargazersDataSource(compositeDisposable)
  }
}
