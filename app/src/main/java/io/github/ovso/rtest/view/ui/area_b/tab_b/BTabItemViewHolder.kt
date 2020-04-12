package io.github.ovso.rtest.view.ui.area_b.tab_b

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.rtest.R
import io.github.ovso.rtest.data.view.BTabItemModel
import io.github.ovso.rtest.exts.load
import io.github.ovso.rtest.utils.rx.RxBus
import io.github.ovso.rtest.view.ui.area_a.AAreaViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_b_tab_fragment_item.*

class BTabItemViewHolder private constructor(override val containerView: View?) :
  RecyclerView.ViewHolder(containerView!!), LayoutContainer {

  fun bind(bTabItemModel: BTabItemModel) {
    iv_b_tab_item_item.load(bTabItemModel.avatar_url)
    tv_b_tab_item_item.text = bTabItemModel.name
    setClickEvent(bTabItemModel)
  }

  private fun setClickEvent(bTabItemModel: BTabItemModel) {
    itemView.setOnClickListener {
      RxBus.send(
        AAreaViewModel.AAreaModel(
          avatarUrl = bTabItemModel.avatar_url,
          name = bTabItemModel.name
        )
      )
    }
  }

  fun onViewRecycled() {
  }

  companion object {
    fun create(parent: ViewGroup): BTabItemViewHolder {
      return BTabItemViewHolder(
        LayoutInflater.from(parent.context)
          .inflate(R.layout.item_b_tab_fragment_item, parent, false)
      )
    }
  }
}
