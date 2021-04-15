package io.ebay.imagegallery.di.component

import android.app.Application
import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import dagger.Component
import io.ebay.imagegallery.ImageGalleryApp
import io.ebay.imagegallery.data.remote.NetworkService
import io.ebay.imagegallery.data.repository.VehicleRepository
import io.ebay.imagegallery.di.ApplicationContext
import io.ebay.imagegallery.di.module.ApplicationModule
import io.ebay.imagegallery.utils.network.NetworkHelper
import io.ebay.imagegallery.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app : ImageGalleryApp)

    fun getApplication() : Application

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkHelper(): NetworkHelper

    fun getNetworkService() : NetworkService

    fun getVehicleRepository() : VehicleRepository

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable

    fun getCircularProgressDrawable(): CircularProgressDrawable
}