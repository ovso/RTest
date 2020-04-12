package io.github.ovso.rtest.view.ui.area_b.tab_b

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.rtest.R
import io.github.ovso.rtest.data.datasource.StargazersDataSourceFactory
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.model.BStargazer
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.exts.load
import io.github.ovso.rtest.utils.rx.RxBus
import io.github.ovso.rtest.view.ui.area_a.AAreaViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_b_tab_fragment.*

class BTabViewHolder private constructor(override val containerView: View?) :
  RecyclerView.ViewHolder(containerView!!),
  LayoutContainer {
  private val compositeDisposable = CompositeDisposable()
  private val repository = GithubRepository()
  private var adapter: BTabItemPagedListAdapter? = null

  fun bind(bStargazer: BStargazer?) {
    adapter = BTabItemPagedListAdapter()
    bStargazer?.let {
      tv_b_tab_item.text = it.login
      iv_b_tab_item.load(it.avatarUrl)
      setClickEvent(it)
    }
  }

  private fun setClickEvent(bStargazer: BStargazer) {
    itemView.setOnClickListener {
      RxBus.send(
        AAreaViewModel.AAreaModel(
          avatarUrl = bStargazer.avatarUrl,
          name = bStargazer.login
        )
      )
    }
  }

  private fun getLifecycleOwner(v: View): LifecycleOwner? = (v.context as? LifecycleOwner)

  fun onViewRecycled() {
    compositeDisposable.clear()
  }

  companion object {

    fun create(parent: ViewGroup): BTabViewHolder {
      return BTabViewHolder(
        LayoutInflater.from(parent.context).inflate(
          R.layout.item_b_tab_fragment,
          parent,
          false
        )
      )
    }

    fun toColor(repo: Repo): Int {
      return if (repo.stargazers_count > 50) Color.RED else Color.DKGRAY
    }
  }
}
