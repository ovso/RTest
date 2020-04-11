package io.github.ovso.rtest.view.ui.area_b.tab_b

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import io.github.ovso.rtest.data.network.model.Repo

val bTabDiffUtil = object : DiffUtil.ItemCallback<Repo>() {
  override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
    return areItemsTheSame(oldItem, newItem)
  }
}

class BTabPagedListAdapter : PagedListAdapter<Repo, BTabViewHolder>(bTabDiffUtil) {
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
