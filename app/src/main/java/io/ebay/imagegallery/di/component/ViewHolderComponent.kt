package io.ebay.imagegallery.di.component



import dagger.Component
import io.ebay.imagegallery.di.ViewModelScope
import io.ebay.imagegallery.di.module.ViewHolderModule
import io.ebay.imagegallery.ui.home.list.VehicleItemViewHolder

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {
    fun inject(viewHolder: VehicleItemViewHolder)


}