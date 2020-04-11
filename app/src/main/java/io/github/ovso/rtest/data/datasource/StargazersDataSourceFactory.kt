package io.github.ovso.rtest.data.datasource

import androidx.paging.DataSource
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.model.Stargazer
import io.reactivex.rxjava3.disposables.CompositeDisposable

class StargazersDataSourceFactory(
  private val compositeDisposable: CompositeDisposable,
  private val repository: GithubRepository,
  private val repoName: String
) : DataSource.Factory<Int, Stargazer>() {

  override fun create(): DataSource<Int, Stargazer> {
    return StargazersDataSource(compositeDisposable, repository, repoName)
  }
}
