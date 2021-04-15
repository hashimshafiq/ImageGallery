package io.ebay.imagegallery.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import io.ebay.imagegallery.data.repository.VehicleRepository
import io.ebay.imagegallery.ui.base.BaseActivity
import io.ebay.imagegallery.ui.home.HomeViewModel
import io.ebay.imagegallery.utils.ViewModelProviderFactory
import io.ebay.imagegallery.utils.network.NetworkHelper
import io.ebay.imagegallery.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


@Module
class ActivityModule(private val activity : BaseActivity<*>) {


    @Provides
    fun providesHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        vehicleRepository: VehicleRepository
    ): HomeViewModel = ViewModelProvider(
        activity,
        ViewModelProviderFactory(HomeViewModel::class) {
            HomeViewModel(schedulerProvider, compositeDisposable, networkHelper,vehicleRepository)
        }).get(HomeViewModel::class.java)


}