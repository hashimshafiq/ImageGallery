package io.ebay.imagegallery.ui.home

import io.ebay.imagegallery.R
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


    override fun onCreate() {
        fetchVehicleDetails()
    }


    private fun fetchVehicleDetails(){

        if (checkInternetConnection()) {

            compositeDisposable.addAll(
                vehicleRepository.fetchCarDetails()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            messageString.postValue(Resource.success("Successfull"))
                        },
                        {
                            handleNetworkError(it)
                        }
                    )
                )

        }else{
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
        }








    }
}