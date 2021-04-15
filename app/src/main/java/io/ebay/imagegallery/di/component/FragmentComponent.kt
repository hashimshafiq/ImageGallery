package io.ebay.imagegallery.di.component


import dagger.Component
import io.ebay.imagegallery.di.FragmentScope
import io.ebay.imagegallery.di.module.FragmentModule

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {




}