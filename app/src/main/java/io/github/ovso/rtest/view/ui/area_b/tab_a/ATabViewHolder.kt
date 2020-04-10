package io.github.ovso.rtest.view.ui.area_b.tab_a

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.rtest.R
import io.github.ovso.rtest.data.network.model.RepoResponse
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_a_tab_fragment.*

class ATabViewHolder private constructor(override val containerView: View?) :
  RecyclerView.ViewHolder(containerView!!),
  LayoutContainer {

  private val res = itemView.resources

  fun bind(item: RepoResponse) {
    tv_a_tab_item.text =
      res.getString(R.string.a_tab_item_format, item.name, item.description, item.stargazers_count)
    tv_a_tab_item.setTextColor(if (item.stargazers_count > 50) Color.RED else Color.DKGRAY)
  }

  companion object {
    fun create(parent: ViewGroup): ATabViewHolder {
      return ATabViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_a_tab_fragment, parent, false)
      )
    }
  }
}
