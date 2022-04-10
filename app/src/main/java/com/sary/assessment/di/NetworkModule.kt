package com.sary.assessment.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sary.assessment.BuildConfig
import com.sary.assessment.BuildConfig.BASE_URL
import com.sary.assessment.core.api.NetworkRequestsInterceptor
import com.sary.assessment.data.sources.remote.MainApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
  private val CALL_TIMEOUT = if (BuildConfig.DEBUG) 120L else 60L
  private val READ_TIMEOUT = if (BuildConfig.DEBUG) 120L else 60L
  private val WRITE_TIMEOUT = if (BuildConfig.DEBUG) 120L else 60L
  private val CONNECTION_TIMEOUT = if (BuildConfig.DEBUG) 120L else 60L

  @Provides
  fun provideLoggingInterceptor() =
    HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }

  @Provides
  @Singleton
  fun provideNetworkRequestsInterceptor(): NetworkRequestsInterceptor = NetworkRequestsInterceptor()

  @Provides
  @Singleton
  fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    networkRequestsInterceptor: NetworkRequestsInterceptor
  ) = OkHttpClient.Builder()
    .addInterceptor(httpLoggingInterceptor)
    .addInterceptor(networkRequestsInterceptor)
    .callTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
    .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
    .build()

  @Provides
  @Singleton
  fun provideGson(): Gson = GsonBuilder().create()

  @Provides
  @Singleton
  fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
    GsonConverterFactory.create(gson)

  @Provides
  @Singleton
  fun provideMainRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory,
  ): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(gsonConverterFactory)
    .client(okHttpClient)
    .build()

  @Provides
  @Singleton
  fun provideMainApiService(retrofit: Retrofit): MainApiService {
    return retrofit.create(MainApiService::class.java)
  }
}