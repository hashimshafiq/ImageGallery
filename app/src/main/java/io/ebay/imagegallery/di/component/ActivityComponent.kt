package io.ebay.imagegallery.di.component


import dagger.Component
import io.ebay.imagegallery.di.ActivityScope
import io.ebay.imagegallery.di.module.ActivityModule

@ActivityScope
@Component(dependencies = [ApplicationComponent::class],
            modules = [ActivityModule::class])
interface ActivityComponent {



}