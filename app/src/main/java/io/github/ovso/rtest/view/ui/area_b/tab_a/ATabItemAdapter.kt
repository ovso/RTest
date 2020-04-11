package io.github.ovso.rtest.view.ui.area_b.tab_a

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.rtest.data.network.model.Stargazer

class ATabItemAdapter(private var items: List<Stargazer>?) :
  RecyclerView.Adapter<ATabItemViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ATabItemViewHolder =
    ATabItemViewHolder.create(parent)

  override fun getItemCount() = items?.count() ?: 0

  override fun onBindViewHolder(holder: ATabItemViewHolder, position: Int) =
    holder.bind(getItem(position))

  private fun getItem(position: Int): Stargazer = items?.get(position) ?: Stargazer("", 0, "")

  fun clear() {
    items = null
    notifyDataSetChanged()
  }
}
