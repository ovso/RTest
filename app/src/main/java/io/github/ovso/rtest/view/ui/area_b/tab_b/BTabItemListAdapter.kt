package io.github.ovso.rtest.view.ui.area_b.tab_b

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.rtest.data.view.BTabItemModel

val bTabItemDiffUti = object : DiffUtil.ItemCallback<BTabItemModel>() {
  override fun areItemsTheSame(oldItem: BTabItemModel, newItem: BTabItemModel): Boolean {
    return oldItem.name == newItem.name
  }

  override fun areContentsTheSame(oldItem: BTabItemModel, newItem: BTabItemModel): Boolean {
    return areItemsTheSame(oldItem, newItem)
  }
}

class BTabItemListAdapter : ListAdapter<BTabItemModel, BTabItemViewHolder>(bTabItemDiffUti) {
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
