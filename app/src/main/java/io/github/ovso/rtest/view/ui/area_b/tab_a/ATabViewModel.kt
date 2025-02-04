package io.github.ovso.rtest.view.ui.area_b.tab_a

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.github.ovso.rtest.data.datasource.ReposDataSourceFactory
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.view.base.DisposableViewModel

class ATabViewModel : DisposableViewModel() {
  private val repository by lazy { GithubRepository() }
  private var repoList: LiveData<PagedList<Repo>>
  private var sourceFactory: ReposDataSourceFactory

  init {
    sourceFactory = ReposDataSourceFactory(compositeDisposable, repository)
    val config = PagedList.Config.Builder()
      .setPageSize(20)
      .setInitialLoadSizeHint(20)
      .setEnablePlaceholders(false)
      .build()
    repoList = LivePagedListBuilder(sourceFactory, config).build()
  }

  fun getItems(): LiveData<PagedList<Repo>> = repoList
}
