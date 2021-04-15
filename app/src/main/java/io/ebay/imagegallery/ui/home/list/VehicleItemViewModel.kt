package io.ebay.imagegallery.ui.home.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import io.ebay.imagegallery.data.model.ImageDetail
import io.ebay.imagegallery.ui.base.BaseItemViewModel
import io.ebay.imagegallery.utils.network.NetworkHelper
import io.ebay.imagegallery.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class VehicleItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper) : BaseItemViewModel<ImageDetail>(schedulerProvider, compositeDisposable, networkHelper) {

    override fun onCreate() {}

    val imageURL : LiveData<String> = Transformations.map(data){
        it.thumbnailURL
    }



}