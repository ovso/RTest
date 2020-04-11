package io.github.ovso.rtest.view.ui.area_b.tab_b

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.model.Repo

class BTabViewModel : ViewModel() {
  private val repository by lazy { GithubRepository() }

}
