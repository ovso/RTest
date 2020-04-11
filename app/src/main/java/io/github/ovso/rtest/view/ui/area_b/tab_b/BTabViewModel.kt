package io.github.ovso.rtest.view.ui.area_b.tab_b

import android.util.SparseArray
import androidx.lifecycle.ViewModel
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.model.BRepo
import io.github.ovso.rtest.data.network.model.BStargazer

class BTabViewModel : ViewModel() {
  private val repository by lazy { GithubRepository() }

  companion object {
    val stargazersSparseArray = SparseArray<MutableList<BStargazer>>()
    val reposSparseArray = SparseArray<MutableList<BRepo>>()
  }
}
