package io.github.ovso.rtest.view.ui.area_b.tab_a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.rtest.R
import kotlinx.android.extensions.LayoutContainer

class ATabViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!),
  LayoutContainer {

  fun bind(x: Any) {

  }

  companion object {
    fun create(parent: ViewGroup): ATabViewHolder {
      return ATabViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_a_tab_fragment, parent)
      )
    }
  }
}
