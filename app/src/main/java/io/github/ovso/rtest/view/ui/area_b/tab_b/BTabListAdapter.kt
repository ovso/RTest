package io.github.ovso.rtest.view.ui.area_b.tab_b

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.rtest.data.network.model.BStargazer

val bTabDiffUtil = object : DiffUtil.ItemCallback<BStargazer>() {
  override fun areItemsTheSame(oldItem: BStargazer, newItem: BStargazer): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: BStargazer, newItem: BStargazer): Boolean {
    return areItemsTheSame(oldItem, newItem)
  }
}
class BTabListAdapter : ListAdapter<BStargazer, BTabViewHolder>(bTabDiffUtil) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BTabViewHolder {
    return BTabViewHolder.create(parent)
  }

  override fun onBindViewHolder(holder: BTabViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  override fun onViewRecycled(holder: BTabViewHolder) {
    super.onViewRecycled(holder)
    holder.onViewRecycled()
  }
}
