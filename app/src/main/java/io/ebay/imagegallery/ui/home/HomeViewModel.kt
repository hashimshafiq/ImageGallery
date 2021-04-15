package io.ebay.imagegallery.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ebay.imagegallery.R
import io.ebay.imagegallery.data.model.ImageDetail
import io.ebay.imagegallery.data.repository.VehicleRepository
import io.ebay.imagegallery.ui.base.BaseViewModel
import io.ebay.imagegallery.utils.common.Resource
import io.ebay.imagegallery.utils.network.NetworkHelper
import io.ebay.imagegallery.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val vehicleRepository: VehicleRepository

    ) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    private var detailImages = arrayListOf<ImageDetail>()

    private val _detailImageLiveData : MutableLiveData<Resource<List<ImageDetail>>> = MutableLiveData()

    val detailImageLiveData: LiveData<Resource<List<ImageDetail>>>
        get() = _detailImageLiveData


    override fun onCreate() {
        fetchVehicleDetails()
    }


    private fun fetchVehicleDetails(){

        if (checkInternetConnection()) {
            _detailImageLiveData.postValue(Resource.loading())
            compositeDisposable.addAll(
                vehicleRepository.fetchCarDetails()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {  vehicleDetailResponse ->
                            detailImages.clear()
                            vehicleDetailResponse.images.forEach { vehicleImage ->
                                detailImages.add(ImageDetail("https://${vehicleImage.uri}_2.jpg", "https://${vehicleImage.uri}_27.jpg"))
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
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
        }








    }
}