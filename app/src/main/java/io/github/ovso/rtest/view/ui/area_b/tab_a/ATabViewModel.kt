package io.github.ovso.rtest.view.ui.area_b.tab_a

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.github.ovso.rtest.data.datasource.ReposDataSourceFactory
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.view.base.DisposableViewModel

class ATabViewModel : DisposableViewModel() {
  private val repository by lazy { GithubRepository() }
  private val items = MutableLiveData<List<Repo>>()

  private var repoList: LiveData<PagedList<Repo>>
  private var sourceFactory: ReposDataSourceFactory

  init {
    sourceFactory = ReposDataSourceFactory(compositeDisposable, repository)
    val config = PagedList.Config.Builder()
      .setPageSize(30)
      .setInitialLoadSizeHint(30)
      .setEnablePlaceholders(false)
      .build()
    repoList = LivePagedListBuilder(sourceFactory, config).build()
  }

  fun getItems(): LiveData<PagedList<Repo>> = repoList
}
