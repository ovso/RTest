package io.github.ovso.rtest.view.ui.area_b.tab_a

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.rtest.R
import io.github.ovso.rtest.data.datasource.StargazersDataSourceFactory
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.exts.appContext
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_a_tab_fragment.*

class ATabViewHolder private constructor(override val containerView: View?) :
  RecyclerView.ViewHolder(containerView!!),
  LayoutContainer {
  private val compositeDisposable = CompositeDisposable()
  private val repository = GithubRepository()
  private var adapter: ATabItemPagedListAdapter? = null

  fun bind(repo: Repo?) {
    adapter = ATabItemPagedListAdapter()
    repo?.let {
      tv_a_tab_item.text = toText(itemView.appContext(), repo)
      tv_a_tab_item.setTextColor(toColor(repo))
      rv_a_tab_item.adapter = adapter
      reqStargazers(repo)
    }
  }

  private fun reqStargazers(repo: Repo) {
    val sourceFactory = StargazersDataSourceFactory(compositeDisposable, repository, repo.name)
    val config = PagedList.Config.Builder()
      .setPageSize(30)
      .setInitialLoadSizeHint(30)
      .setEnablePlaceholders(false)
      .build()
    val stargazerList = LivePagedListBuilder(sourceFactory, config).build()
    stargazerList.observeForever {
      adapter?.submitList(it)
    }
  }

  fun onViewRecycled() {
    compositeDisposable.clear()
    (rv_a_tab_item.adapter as? ATabItemAdapter)?.clear()
  }

  companion object {

    fun create(parent: ViewGroup): ATabViewHolder {
      return ATabViewHolder(
        LayoutInflater.from(parent.context).inflate(
          R.layout.item_a_tab_fragment,
          parent,
          false
        )
      )
    }

    fun toText(context: Context, repo: Repo) =
      context.getString(
        R.string.a_tab_item_format,
        repo.name,
        repo.description,
        repo.stargazers_count
      )

    fun toColor(repo: Repo): Int {
      return if (repo.stargazers_count > 50) Color.RED else Color.DKGRAY
    }
  }
}
