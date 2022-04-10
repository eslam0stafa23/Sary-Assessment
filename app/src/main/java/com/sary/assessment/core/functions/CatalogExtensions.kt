package com.sary.assessment.core.functions

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sary.assessment.R
import com.sary.assessment.data.models.Catalog
import com.sary.assessment.presentation.adapters.BannerViewAdapter
import com.sary.assessment.presentation.adapters.GroupViewAdapter
import com.sary.assessment.presentation.adapters.SmartViewAdapter

fun Activity.getSmartView(catalog: Catalog): View {
  val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
  val layout = inflater.inflate(R.layout.layout_smart, null)
  val title: TextView = layout.findViewById(R.id.tvSectionTitle)
  val list: RecyclerView = layout.findViewById(R.id.rvItems)
  if (catalog.showTitle) title.text = catalog.title else title.hide()
  list.apply {
    adapter = SmartViewAdapter(catalog.items)
    setHasFixedSize(true)
    layoutManager =
      LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
  }
  return layout
}

fun Activity.getGroupView(catalog: Catalog): View {
  val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
  val layout = inflater.inflate(R.layout.layout_group, null)
  val title: TextView = layout.findViewById(R.id.tvSectionTitle)
  val list: RecyclerView = layout.findViewById(R.id.rvItems)
  if (catalog.showTitle) title.text = catalog.title else title.hide()
  list.apply {
    layoutManager = GridLayoutManager(this@getGroupView, catalog.rowCount)
    adapter = GroupViewAdapter(catalog.notNullItems)
    setHasFixedSize(true)
  }
  return layout
}

fun Activity.getBannerView(catalog: Catalog): View {
  val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
  val layout = inflater.inflate(R.layout.layout_banner, null)
  val list: RecyclerView = layout.findViewById(R.id.rvItems)
  list.apply {
    layoutManager =
      LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
    adapter = BannerViewAdapter(catalog.items)
    setHasFixedSize(true)
  }
  return layout
}