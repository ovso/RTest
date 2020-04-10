package io.github.ovso.rtest.view.ui.area_b.tab_a

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.rtest.R
import io.github.ovso.rtest.data.network.model.ItemModel
import io.github.ovso.rtest.data.network.model.Repo
import io.github.ovso.rtest.exts.appContext
import io.github.ovso.rtest.exts.hDivider
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_a_tab_fragment.*

class ATabViewHolder private constructor(override val containerView: View?) :
  RecyclerView.ViewHolder(containerView!!),
  LayoutContainer {

  fun bind(item: ItemModel) {
    val repo = item.repo
    tv_a_tab_item.text = toText(itemView.appContext(), repo)
    tv_a_tab_item.setTextColor(toColor(repo))
    rv_a_tab_item.run { adapter = toAdapter(item);hDivider() }
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

    fun toAdapter(item: ItemModel): ATabItemAdapter = ATabItemAdapter(item.stargazers)
  }
}
