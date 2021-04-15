package io.ebay.imagegallery

import android.app.Application
import io.ebay.imagegallery.di.component.ApplicationComponent
import io.ebay.imagegallery.di.component.DaggerApplicationComponent
import io.ebay.imagegallery.di.module.ApplicationModule

class ImageGalleryApp : Application() {


    lateinit var applicationComponent: ApplicationComponent

    companion object {
        const val TAG = "ImageGalleryApp"
    }


    override fun onCreate() {
        injectDependencies()
        super.onCreate()


    }


    private fun injectDependencies(){
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()

        applicationComponent.inject(this)
    }

    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }

}