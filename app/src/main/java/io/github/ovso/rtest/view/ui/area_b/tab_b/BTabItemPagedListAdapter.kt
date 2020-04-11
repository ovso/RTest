package io.github.ovso.rtest.view.ui.area_b.tab_b

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import io.github.ovso.rtest.data.network.model.Stargazer

val bTabItemDiffUti = object : DiffUtil.ItemCallback<Stargazer>() {
  override fun areItemsTheSame(oldItem: Stargazer, newItem: Stargazer): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Stargazer, newItem: Stargazer): Boolean {
    return areItemsTheSame(oldItem, newItem)
  }
}

class BTabItemPagedListAdapter : PagedListAdapter<Stargazer, BTabItemViewHolder>(bTabItemDiffUti) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BTabItemViewHolder {
    return BTabItemViewHolder.create(parent)
  }

  override fun onBindViewHolder(holder: BTabItemViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  override fun onViewRecycled(holder: BTabItemViewHolder) {
    super.onViewRecycled(holder)
    holder.onViewRecycled()
  }
}
