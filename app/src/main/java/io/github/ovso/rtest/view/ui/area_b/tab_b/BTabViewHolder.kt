package io.github.ovso.rtest.view.ui.area_b.tab_b

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.rtest.App
import io.github.ovso.rtest.R
import io.github.ovso.rtest.data.network.model.BStargazer
import io.github.ovso.rtest.data.view.BTabItemModel
import io.github.ovso.rtest.exts.load
import io.github.ovso.rtest.utils.rx.RxBus
import io.github.ovso.rtest.view.ui.area_a.AAreaViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_b_tab_fragment.*

class BTabViewHolder private constructor(override val containerView: View?) :
  RecyclerView.ViewHolder(containerView!!),
  LayoutContainer {
  private val compositeDisposable = CompositeDisposable()
  private var adapter: BTabItemListAdapter? = null

  fun bind(bStargazer: BStargazer?) {
    adapter = BTabItemListAdapter()
    bStargazer?.let {
      tv_b_tab_item.text = it.login
      iv_b_tab_item.load(it.avatarUrl)
      rv_b_tab_item.adapter = adapter
      setClickEvent(it)
      observe(it)
    }
  }

  private fun observe(bStargazer: BStargazer) {
    getLifecycleOwner(itemView)?.let { lifecycleOwner ->

      App.appDb.stargazers().stargazers(bStargazer.login)
        .observe(lifecycleOwner, Observer { entityList ->
          val bTabItemModels = mutableListOf<BTabItemModel>()
          entityList.forEach {
            bTabItemModels.add(
              BTabItemModel(name = it.repoName, avatar_url = it.repoAvatarUrl)
            )
          }
          adapter?.submitList(bTabItemModels)
        })

    }

  }

  private fun setClickEvent(bStargazer: BStargazer) {
    itemView.setOnClickListener {
      RxBus.send(
        AAreaViewModel.AAreaModel(
          avatarUrl = bStargazer.avatarUrl,
          name = bStargazer.login
        )
      )
    }
  }


  private fun getLifecycleOwner(v: View): LifecycleOwner? = (v.context as? LifecycleOwner)

  fun onViewRecycled() {
    compositeDisposable.clear()
  }

  companion object {

    fun create(parent: ViewGroup): BTabViewHolder {
      return BTabViewHolder(
        LayoutInflater.from(parent.context).inflate(
          R.layout.item_b_tab_fragment,
          parent,
          false
        )
      )
    }
  }
}
