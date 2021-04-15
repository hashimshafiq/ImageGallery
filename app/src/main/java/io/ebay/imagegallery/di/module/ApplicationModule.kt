package io.ebay.imagegallery.di.module

import android.app.Application
import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import dagger.Module
import dagger.Provides
import io.ebay.imagegallery.BuildConfig
import io.ebay.imagegallery.ImageGalleryApp
import io.ebay.imagegallery.data.remote.NetworkService
import io.ebay.imagegallery.data.remote.Networking
import io.ebay.imagegallery.di.ApplicationContext
import io.ebay.imagegallery.utils.network.NetworkHelper
import io.ebay.imagegallery.utils.rx.RxSchedulerProvider
import io.ebay.imagegallery.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application : ImageGalleryApp) {

    @Singleton
    @Provides
    fun provideApplication(): Application = application


    @Singleton
    @ApplicationContext
    @Provides
    fun provideContext(): Context = application

    @Singleton
    @Provides
    fun providesNetworkHelper() = NetworkHelper(application)

    @Singleton
    @Provides
    fun providesNetworkService() : NetworkService =
        Networking.create(
            BuildConfig.BASE_URL,
            application.cacheDir,
            10*1024*1024 //10MB
        )

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    fun providesCircularProgressBar() : CircularProgressDrawable {
        return CircularProgressDrawable(application).apply {
            strokeWidth = 5f
            centerRadius = 50f
        }.also {
            it.start()
        }
    }
}