package io.ebay.imagegallery.ui.detail

import android.os.Bundle
import android.view.View
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import io.ebay.imagegallery.data.model.ImageDetail
import io.ebay.imagegallery.databinding.ActivityVehicleDetailBinding
import io.ebay.imagegallery.di.component.ActivityComponent
import io.ebay.imagegallery.ui.base.BaseActivity
import javax.inject.Inject

class VehicleDetailActivity : BaseActivity<VehicleDetailViewModel>() {

    lateinit var binding: ActivityVehicleDetailBinding

    @Inject
    lateinit var circularProgressDrawable: CircularProgressDrawable

    override fun provideLayoutView(): View {
        binding = ActivityVehicleDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

        val imageDetail = intent.extras?.getParcelable<ImageDetail>("data")
            ?: throw IllegalArgumentException("post must be non-null")





        Glide
            .with(applicationContext)
            .load(imageDetail.highResURL)
            .placeholder(circularProgressDrawable)
            .into(binding.ivHighRes)
    }




}