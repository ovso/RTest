package io.github.ovso.rtest.view.ui.area_b.tab_a

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.rtest.R
import io.github.ovso.rtest.data.network.GithubRepository
import io.github.ovso.rtest.data.network.User
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.data.network.model.Stargazer
import io.github.ovso.rtest.exts.appContext
import io.github.ovso.rtest.exts.hDivider
import io.github.ovso.rtest.utils.rx.SchedulerProvider
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_a_tab_fragment.*

class ATabViewHolder private constructor(override val containerView: View?) :
  RecyclerView.ViewHolder(containerView!!),
  LayoutContainer {
  private val compositeDisposable = CompositeDisposable()
  private val repository = GithubRepository()
  fun bind(repo: Repo) {
    tv_a_tab_item.text = toText(itemView.appContext(), repo)
    tv_a_tab_item.setTextColor(toColor(repo))
    reqStargazers(repo)
  }

  private fun reqStargazers(repo: Repo) {
    fun onSuccess(items: List<Stargazer>) {
      println(items.size)
      rv_a_tab_item.run { adapter = toAdapter(items);hDivider() }
    }

    fun onFailure(t: Throwable) {
      println(t.message)
    }

    compositeDisposable += repository.api()
      .stargazers(User.name, repo.name, 1, 30)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)
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

    fun toAdapter(stargazers: List<Stargazer>): ATabItemAdapter = ATabItemAdapter(stargazers)
  }
}

private operator fun CompositeDisposable.plusAssign(subscribe: @NonNull Disposable?) {
  add(subscribe)
}
