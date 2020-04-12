package io.github.ovso.rtest.view.ui.area_b.tab_b

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.github.ovso.rtest.App
import io.github.ovso.rtest.R
import kotlinx.android.synthetic.main.fragment_tab_b.*
import timber.log.Timber

class BTabFragment : Fragment() {

  companion object {
    fun newInstance() = BTabFragment()
  }

  private val adapter by lazy { BTabPagedListAdapter() }
  private lateinit var viewModel: BTabViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_tab_b, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProvider(this).get(BTabViewModel::class.java)
    rv_b_tab.adapter = adapter

    App.appDb.repos().repos2().observe(viewLifecycleOwner, Observer {
      Timber.d("observe = ${it.count()}")
      println("ThreadName = ${Thread.currentThread().name}")
    })
  }

  override fun onDestroyView() {
    super.onDestroyView()
    Thread { App.appDb.repos().removeAll() }.start()
  }
}
