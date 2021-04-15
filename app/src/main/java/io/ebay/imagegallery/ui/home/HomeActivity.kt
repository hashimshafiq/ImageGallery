package io.ebay.imagegallery.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.ebay.imagegallery.R
import io.ebay.imagegallery.data.model.ImageDetail
import io.ebay.imagegallery.databinding.ActivityHomeBinding
import io.ebay.imagegallery.di.component.ActivityComponent
import io.ebay.imagegallery.ui.base.BaseActivity
import io.ebay.imagegallery.ui.detail.VehicleDetailActivity
import io.ebay.imagegallery.ui.home.list.VehicleAdapter
import io.ebay.imagegallery.utils.common.Status
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeViewModel>() {

    private lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

    lateinit var vehicleAdapter: VehicleAdapter

    override fun provideLayoutView(): View {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

        vehicleAdapter = VehicleAdapter(this.lifecycle, arrayListOf()) { imageDetail, imageView ->

            val intent = Intent(this, VehicleDetailActivity::class.java)
            intent.putExtra("data", imageDetail)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                imageView,
                imageView.transitionName
            )
            startActivity(intent, options.toBundle())


        }


        binding.rvVehicleThumbnails.apply {
            layoutManager = staggeredGridLayoutManager
            adapter = vehicleAdapter
        }

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.detailImageLiveData.observe(this, {
            when(it.status){
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { thumbnailList ->  vehicleAdapter.appendData(thumbnailList) }
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    showMessage(it.data.toString())
                }
                Status.UNKNOWN -> {
                    showMessage(R.string.network_default_error)
                }

            }
        })
    }
}