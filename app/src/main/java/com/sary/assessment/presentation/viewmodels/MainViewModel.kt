package com.sary.assessment.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sary.assessment.core.api.NetworkState
import com.sary.assessment.data.models.Banner
import com.sary.assessment.data.models.Catalog
import com.sary.assessment.domain.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

  private val disposables = CompositeDisposable()
  private val _bannersList: MutableLiveData<NetworkState<List<Banner>>> =
    MutableLiveData<NetworkState<List<Banner>>>()
  val bannersList: LiveData<NetworkState<List<Banner>>> = _bannersList

  private val _catalogsList: MutableLiveData<NetworkState<List<Catalog>>> =
    MutableLiveData<NetworkState<List<Catalog>>>()
  val catalogsList: LiveData<NetworkState<List<Catalog>>> = _catalogsList

  fun getBanners() {
    disposables.add(
      mainRepository.getBanners()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { _bannersList.value = NetworkState.Loading() }
        .subscribe ({
          _bannersList.value = it
        }) {
          _bannersList.value = NetworkState.Error()
        }
    )
  }

  fun getCatalogs() {
    disposables.add(
      mainRepository.getCatalog()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { _bannersList.value = NetworkState.Loading() }
        .subscribe({
          _catalogsList.value = it
        }) {
          _catalogsList.value = NetworkState.Error()
        }
    )
  }

  override fun onCleared() {
    super.onCleared()
    disposables.dispose()
  }
}