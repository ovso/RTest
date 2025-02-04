package io.github.ovso.rtest.view.ui.area_b.tab_a

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import io.github.ovso.rtest.data.network.model.Repo

val repoDiffUtil = object : DiffUtil.ItemCallback<Repo>() {
  override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
    return areItemsTheSame(oldItem, newItem)
  }
}

class ATabPagedListAdapter : PagedListAdapter<Repo, ATabViewHolder>(repoDiffUtil) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ATabViewHolder {
    return ATabViewHolder.create(parent)
  }

  override fun onBindViewHolder(holder: ATabViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  override fun onViewRecycled(holder: ATabViewHolder) {
    super.onViewRecycled(holder)
    holder.onViewRecycled()
  }
}
