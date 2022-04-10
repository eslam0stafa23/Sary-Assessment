package com.sary.assessment.data.sources.remote

import com.sary.assessment.data.models.GetBannersResponse
import com.sary.assessment.data.models.GetCatalogResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface MainApiService {

  @GET("v2.5.1/baskets/325514/banners/")
  fun getBanners(): Flowable<GetBannersResponse>

  @GET("baskets/325514/catalog/")
  fun getCatalog(): Flowable<GetCatalogResponse>
}