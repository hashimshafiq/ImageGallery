package io.ebay.imagegallery.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ebay.imagegallery.R
import io.ebay.imagegallery.data.model.ImageDetail
import io.ebay.imagegallery.data.repository.VehicleRepository
import io.ebay.imagegallery.ui.base.BaseViewModel
import io.ebay.imagegallery.utils.common.Event
import io.ebay.imagegallery.utils.common.Resource
import io.ebay.imagegallery.utils.network.NetworkHelper
import io.ebay.imagegallery.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class VehicleListViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val vehicleRepository: VehicleRepository

    ) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    private var detailImages = arrayListOf<ImageDetail>()

    private val _detailImageLiveData : MutableLiveData<Resource<List<ImageDetail>>> = MutableLiveData()

    val detailImageLiveData: LiveData<Resource<List<ImageDetail>>>
        get() = _detailImageLiveData

    private val _noInternetLayout : MutableLiveData<Event<Boolean>> = MutableLiveData()

    val noInternetLayout : LiveData<Event<Boolean>>
        get() = _noInternetLayout


    override fun onCreate() {
        fetchVehicleDetails()
    }


    fun fetchVehicleDetails(){

        if (checkInternetConnection()) {
            _noInternetLayout.postValue(Event(false))
            _detailImageLiveData.postValue(Resource.loading())
            compositeDisposable.addAll(
                vehicleRepository.fetchCarDetails()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {  vehicleDetailResponse ->
                            detailImages.clear()
                            vehicleDetailResponse.images.forEach { vehicleImage ->
                                detailImages.add(ImageDetail(vehicleImage.uri.getThumbnailURLLink(), vehicleImage.uri.getHighResImageURL()))
                            }

                            _detailImageLiveData.postValue(Resource.success(detailImages))
                        },
                        {
                            handleNetworkError(it)
                            _detailImageLiveData.postValue(Resource.error())
                        }
                    )
                )

        }else{
            _noInternetLayout.postValue(Event(true))
        }

    }


}