package io.github.ovso.rtest.view.ui.area_b.tab_a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.rtest.R
import io.github.ovso.rtest.data.network.model.Stargazer
import io.github.ovso.rtest.exts.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_a_tab_fragment_item.*

class ATabItemViewHolder private constructor(override val containerView: View?) :
  RecyclerView.ViewHolder(containerView!!), LayoutContainer {

  fun bind(stargazer: Stargazer?) {
    stargazer?.let {
      iv_a_tab_item_item.load(stargazer.avatarUrl)
      tv_a_tab_item_item.text = stargazer.login
    }
  }

  fun onViewRecycled() {

  }

  companion object {
    fun create(parent: ViewGroup): ATabItemViewHolder {
      return ATabItemViewHolder(
        LayoutInflater.from(parent.context)
          .inflate(R.layout.item_a_tab_fragment_item, parent, false)
      )
    }
  }
}
