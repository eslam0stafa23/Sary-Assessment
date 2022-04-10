package com.sary.assessment.data

import com.sary.assessment.core.api.NetworkState
import com.sary.assessment.data.models.Banner
import com.sary.assessment.data.models.Catalog
import com.sary.assessment.data.sources.remote.MainApiService
import com.sary.assessment.domain.MainRepository
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val mainApiService: MainApiService) :
  MainRepository {

  override fun getBanners(): Flowable<NetworkState<List<Banner>>> {
    return mainApiService.getBanners()
      .subscribeOn(Schedulers.io())
      .map {
        if (it.status) {
          NetworkState.Success(it.result)
        } else {
          NetworkState.Error()
        }
      }
  }

  override fun getCatalog(): Flowable<NetworkState<List<Catalog>>> {
    return mainApiService.getCatalog()
      .subscribeOn(Schedulers.io())
      .map {
        if (it.status) {
          NetworkState.Success(it.catalogs)
        } else {
          NetworkState.Error()
        }
      }
  }
}