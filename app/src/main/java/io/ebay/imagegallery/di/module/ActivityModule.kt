package io.ebay.imagegallery.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.Module
import dagger.Provides
import io.ebay.imagegallery.data.repository.VehicleRepository
import io.ebay.imagegallery.ui.base.BaseActivity
import io.ebay.imagegallery.ui.detail.VehicleDetailViewModel
import io.ebay.imagegallery.ui.home.VehicleListViewModel
import io.ebay.imagegallery.utils.ViewModelProviderFactory
import io.ebay.imagegallery.utils.network.NetworkHelper
import io.ebay.imagegallery.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


@Module
class ActivityModule(private val activity : BaseActivity<*>) {

    companion object {
        const val SPAN_COUNT = 2
        const val ORIENTATION = StaggeredGridLayoutManager.VERTICAL
    }

    @Provides
    fun provideStaggeredGridLayoutManager(): StaggeredGridLayoutManager = StaggeredGridLayoutManager(SPAN_COUNT,ORIENTATION)


    @Provides
    fun providesHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        vehicleRepository: VehicleRepository
    ): VehicleListViewModel = ViewModelProvider(
        activity,
        ViewModelProviderFactory(VehicleListViewModel::class) {
            VehicleListViewModel(schedulerProvider, compositeDisposable, networkHelper,vehicleRepository)
        }).get(VehicleListViewModel::class.java)


    @Provides
    fun providesVehicleDetailViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper

    ): VehicleDetailViewModel = ViewModelProvider(
        activity,
        ViewModelProviderFactory(VehicleDetailViewModel::class) {
            VehicleDetailViewModel(schedulerProvider, compositeDisposable, networkHelper)
        }).get(VehicleDetailViewModel::class.java)


}