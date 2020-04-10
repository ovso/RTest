package io.github.ovso.rtest.view.ui.area_b.tab_a

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.rtest.data.network.model.RepoResponse

class ATabAdapter : ListAdapter<RepoResponse, ATabViewHolder>(diffUtil) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ATabViewHolder {
    return ATabViewHolder.create(parent)
  }

  override fun onBindViewHolder(holder: ATabViewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}

val diffUtil = object : DiffUtil.ItemCallback<RepoResponse>() {
  override fun areItemsTheSame(oldItem: RepoResponse, newItem: RepoResponse): Boolean {
    return oldItem.id == oldItem.id
  }

  override fun areContentsTheSame(oldItem: RepoResponse, newItem: RepoResponse): Boolean {
    return areItemsTheSame(oldItem, newItem)
  }

}
