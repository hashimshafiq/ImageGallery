package io.ebay.imagegallery.di.component

import dagger.Component
import io.ebay.imagegallery.ImageGalleryApp
import io.ebay.imagegallery.di.module.ApplicationModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app : ImageGalleryApp)
}