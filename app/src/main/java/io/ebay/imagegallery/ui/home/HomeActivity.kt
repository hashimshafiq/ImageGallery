package io.ebay.imagegallery.ui.home

import android.os.Bundle
import android.view.View
import io.ebay.imagegallery.databinding.ActivityHomeBinding
import io.ebay.imagegallery.di.component.ActivityComponent
import io.ebay.imagegallery.ui.base.BaseActivity

class HomeActivity : BaseActivity<HomeViewModel>() {

    private lateinit var binding: ActivityHomeBinding

    override fun provideLayoutView(): View {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

    }
}