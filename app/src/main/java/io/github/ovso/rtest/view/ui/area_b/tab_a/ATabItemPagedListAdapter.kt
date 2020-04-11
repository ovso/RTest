package io.github.ovso.rtest.view.ui.area_b.tab_a

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import io.github.ovso.rtest.data.network.model.Stargazer

val stargazerDiffUtil = object : DiffUtil.ItemCallback<Stargazer>() {
  override fun areItemsTheSame(oldItem: Stargazer, newItem: Stargazer): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Stargazer, newItem: Stargazer): Boolean {
    return areItemsTheSame(oldItem, newItem)
  }
}

class ATabItemPagedListAdapter : PagedListAdapter<Stargazer, ATabItemViewHolder>(stargazerDiffUtil) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ATabItemViewHolder {
    return ATabItemViewHolder.create(parent)
  }

  override fun onBindViewHolder(holder: ATabItemViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  override fun onViewRecycled(holder: ATabItemViewHolder) {
    super.onViewRecycled(holder)
    holder.onViewRecycled()
  }
}
