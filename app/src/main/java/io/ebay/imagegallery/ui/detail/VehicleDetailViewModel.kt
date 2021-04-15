package io.ebay.imagegallery.ui.detail

import io.ebay.imagegallery.ui.base.BaseViewModel
import io.ebay.imagegallery.utils.network.NetworkHelper
import io.ebay.imagegallery.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class VehicleDetailViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper)  {


    override fun onCreate() {

    }
}