package io.github.ovso.rtest.data.datasource

import androidx.paging.DataSource
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.model.Repo
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ReposDataSourceFactory(
  private val compositeDisposable: CompositeDisposable,
  private val repository: GithubRepository
) : DataSource.Factory<Int, Repo>() {

  override fun create(): DataSource<Int, Repo> {
    return ReposDataSource(compositeDisposable, repository)
  }
}
