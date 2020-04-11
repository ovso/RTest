package io.github.ovso.rtest.view.ui.area_b.tab_a

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.github.ovso.rtest.data.datasource.ReposDataSourceFactory
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.User
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.exts.plusAssign
import io.github.ovso.rtest.utils.rx.SchedulerProvider
import io.github.ovso.rtest.view.base.DisposableViewModel

class ATabViewModel : DisposableViewModel() {
  private val repository by lazy { GithubRepository() }
  private val items = MutableLiveData<List<Repo>>()

  private var repoList: LiveData<PagedList<Repo>>
  private val pageSize = 15
  private var sourceFactory: ReposDataSourceFactory

  init {
    sourceFactory = ReposDataSourceFactory(compositeDisposable, repository)
    val config = PagedList.Config.Builder()
      .setPageSize(30)
      .setInitialLoadSizeHint(30)
      .setEnablePlaceholders(false)
      .build()
    repoList = LivePagedListBuilder(sourceFactory, config).build()
//    reqItems()
  }

  private fun reqItems() {
    fun onSuccess(_items: List<Repo>) {
      items.value = _items
    }

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    compositeDisposable += repository.api().userRepos(User.name, 1, 30)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)

  }

  fun getItems(): LiveData<PagedList<Repo>> = repoList
}
