package io.github.ovso.rtest.view.ui.area_b.tab_a

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.rtest.data.network.model.ItemModel

class ATabAdapter : ListAdapter<ItemModel, ATabViewHolder>(diffUtil) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ATabViewHolder {
    return ATabViewHolder.create(parent)
  }

  override fun onBindViewHolder(holder: ATabViewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}

val diffUtil = object : DiffUtil.ItemCallback<ItemModel>() {
  override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
    return oldItem.repo.id == newItem.repo.id
  }

  override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
    return areItemsTheSame(oldItem, newItem)
  }

}
