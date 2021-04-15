package io.ebay.imagegallery.di.module

import androidx.lifecycle.LifecycleRegistry
import dagger.Module
import dagger.Provides
import io.ebay.imagegallery.di.ViewModelScope
import io.ebay.imagegallery.ui.base.BaseItemViewHolder

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)

}