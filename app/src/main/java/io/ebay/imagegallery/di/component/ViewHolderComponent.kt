package io.ebay.imagegallery.di.component



import dagger.Component
import io.ebay.imagegallery.di.ViewModelScope
import io.ebay.imagegallery.di.module.ViewHolderModule

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {




}