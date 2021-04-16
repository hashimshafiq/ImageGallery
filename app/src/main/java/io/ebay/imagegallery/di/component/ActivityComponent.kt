package io.ebay.imagegallery.di.component


import dagger.Component
import io.ebay.imagegallery.di.ActivityScope
import io.ebay.imagegallery.di.module.ActivityModule
import io.ebay.imagegallery.ui.detail.VehicleDetailActivity
import io.ebay.imagegallery.ui.home.VehicleListActivity

@ActivityScope
@Component(dependencies = [ApplicationComponent::class],
            modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: VehicleListActivity)
    fun inject(activity: VehicleDetailActivity)


}