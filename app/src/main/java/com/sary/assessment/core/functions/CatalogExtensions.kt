package com.sary.assessment.core.functions

import android.app.Activity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sary.assessment.data.models.Catalog
import com.sary.assessment.databinding.LayoutBannerBinding
import com.sary.assessment.databinding.LayoutGroupBinding
import com.sary.assessment.databinding.LayoutSmartBinding
import com.sary.assessment.presentation.adapters.BannerViewAdapter
import com.sary.assessment.presentation.adapters.GroupViewAdapter
import com.sary.assessment.presentation.adapters.SmartViewAdapter

fun Activity.getSmartView(catalog: Catalog) = LayoutSmartBinding.inflate(layoutInflater).apply {
  if (catalog.showTitle) tvSectionTitle.text = catalog.title else tvSectionTitle.hide()
  rvItems.apply {
    adapter = SmartViewAdapter(catalog.items)
    setHasFixedSize(true)
    layoutManager =
      LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
  }
}.root

fun Activity.getGroupView(catalog: Catalog) = LayoutGroupBinding.inflate(layoutInflater).apply {
  if (catalog.showTitle) tvSectionTitle.text = catalog.title else tvSectionTitle.hide()
  rvItems.apply {
    layoutManager = GridLayoutManager(this@getGroupView, catalog.rowCount)
    adapter = GroupViewAdapter(catalog.notNullItems)
    setHasFixedSize(true)
  }
}.root

fun Activity.getBannerView(catalog: Catalog) = LayoutBannerBinding.inflate(layoutInflater).apply {
  rvItems.apply {
    layoutManager =
      LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
    adapter = BannerViewAdapter(catalog.items)
    setHasFixedSize(true)
  }
}.root
