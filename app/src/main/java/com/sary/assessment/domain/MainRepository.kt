package com.sary.assessment.domain

import com.sary.assessment.core.api.NetworkState
import com.sary.assessment.data.models.Banner
import com.sary.assessment.data.models.Catalog
import io.reactivex.Flowable

interface MainRepository {
  fun getBanners(): Flowable<NetworkState<List<Banner>>>
  fun getCatalog(): Flowable<NetworkState<List<Catalog>>>
}