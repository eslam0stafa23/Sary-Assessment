package com.sary.assessment.core.api

import com.sary.assessment.core.Constants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkRequestsInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val request: Request = chain.request()
    val requestBuilder: Request.Builder = request.newBuilder()
    requestBuilder.addHeader(Constants.DEVICE_TYPE_KEY, Constants.DEVICE_TYPE)
    requestBuilder.addHeader(Constants.APP_VERSION_KEY, Constants.APP_VERSION)
    requestBuilder.addHeader(Constants.ACCEPT_LANGUAGE_KEY, Constants.ACCEPT_LANGUAGE)
    requestBuilder.addHeader(Constants.PLATFORM_KEY, Constants.PLATFORM)
    requestBuilder.addHeader(Constants.AUTHORIZATION_KEY, Constants.TOKEN)
    return chain.proceed(requestBuilder.build())
  }
}