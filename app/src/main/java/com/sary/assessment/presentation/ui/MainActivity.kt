package com.sary.assessment.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.sary.assessment.R
import com.sary.assessment.core.api.NetworkState
import com.sary.assessment.core.functions.addIndicators
import com.sary.assessment.core.functions.getBannerView
import com.sary.assessment.core.functions.getGroupView
import com.sary.assessment.core.functions.getSmartView
import com.sary.assessment.core.functions.isNetworkConnected
import com.sary.assessment.core.functions.setImages
import com.sary.assessment.core.functions.showConnectionError
import com.sary.assessment.core.functions.showErrorMessage
import com.sary.assessment.core.functions.startLoading
import com.sary.assessment.core.functions.stopLoading
import com.sary.assessment.core.views.BaseActivity
import com.sary.assessment.data.models.Banner
import com.sary.assessment.data.models.Catalog
import com.sary.assessment.databinding.ActivityMainBinding
import com.sary.assessment.presentation.annotations.CatalogDataType
import com.sary.assessment.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

  override fun onBind() = ActivityMainBinding.inflate(layoutInflater)

  private val mainViewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    checkConnectivity()
    observeUiState()
  }

  private fun checkConnectivity() {
    if (isNetworkConnected()) {
      fetchBanners()
      fetchCatalogs()
    } else {
      binding?.apply {
        layoutState.showConnectionError(
          getString(R.string.internet_error),
          getString(R.string.try_again)
        ) { checkConnectivity() }
      }
    }
  }

  private fun fetchBanners() = mainViewModel.getBanners()

  private fun fetchCatalogs() = mainViewModel.getCatalogs()

  private fun observeUiState() {
    mainViewModel.bannersList.observe(this, ::handleBannersResult)
    mainViewModel.catalogsList.observe(this, ::handleCatalogsResult)
  }

  private fun handleBannersResult(state: NetworkState<List<Banner>>) {
    when (state) {
      is NetworkState.Success -> {
        binding?.layoutState?.stopLoading()
        binding?.apply {
          viewPagerImagesSlider.setImages(state.data)
          rvIndicators.addIndicators(state.data)
        }
      }
      is NetworkState.Loading -> {
        binding?.layoutState?.startLoading()
      }
      is NetworkState.Error -> {
        binding?.layoutState?.showErrorMessage(getString(R.string.generic_error))
      }
    }
  }

  private fun handleCatalogsResult(state: NetworkState<List<Catalog>>) {
    when (state) {
      is NetworkState.Success -> {
        binding?.layoutState?.stopLoading()
        state.data.forEach {
          when (it.dataType) {
            CatalogDataType.SMART -> {
              binding?.layoutMain?.addView(getSmartView(it))
            }
            CatalogDataType.GROUP -> {
              binding?.layoutMain?.addView(getGroupView(it))
            }
            CatalogDataType.BANNER -> {
              binding?.layoutMain?.addView(getBannerView(it))
            }
          }
        }
      }
      is NetworkState.Loading -> {
        binding?.layoutState?.startLoading()
      }
      is NetworkState.Error -> {
        binding?.layoutState?.showErrorMessage(getString(R.string.generic_error))
      }
    }
  }

}