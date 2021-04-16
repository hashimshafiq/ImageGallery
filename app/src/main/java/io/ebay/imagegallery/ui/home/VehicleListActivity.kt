package io.ebay.imagegallery.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.ebay.imagegallery.databinding.ActivityVehicleListBinding
import io.ebay.imagegallery.databinding.CustomNoInternetLayoutBinding
import io.ebay.imagegallery.di.component.ActivityComponent
import io.ebay.imagegallery.ui.base.BaseActivity
import io.ebay.imagegallery.ui.detail.VehicleDetailActivity
import io.ebay.imagegallery.ui.home.list.VehicleAdapter
import io.ebay.imagegallery.utils.common.Status
import javax.inject.Inject

class VehicleListActivity : BaseActivity<VehicleListViewModel>() {

    private lateinit var binding: ActivityVehicleListBinding
    private lateinit var noInternetBinding: CustomNoInternetLayoutBinding

    @Inject
    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager


    lateinit var vehicleAdapter: VehicleAdapter

    override fun provideLayoutView(): View {
        binding = ActivityVehicleListBinding.inflate(layoutInflater)
        noInternetBinding = binding.noInternetLayout
        return binding.root
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

        vehicleAdapter = VehicleAdapter(this.lifecycle, arrayListOf()) { imageDetail, imageView ->

            val intent = Intent(this, VehicleDetailActivity::class.java)
            intent.putExtra("data", imageDetail.highResURL)

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

        noInternetBinding.btnRetry.setOnClickListener {
            viewModel.fetchVehicleDetails()
        }

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.detailImageLiveData.observe(this, {
            when(it.status){
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { thumbnailList ->  vehicleAdapter.updateData(thumbnailList) }
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
                Status.UNKNOWN -> {
                    binding.progressBar.visibility = View.GONE
                }

            }
        })

        viewModel.noInternetLayout.observe(this,{
            it.getIfNotHandled()?.run {
                if (this){
                    noInternetBinding.root.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.rvVehicleThumbnails.visibility = View.GONE

                }else{
                    noInternetBinding.root.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvVehicleThumbnails.visibility = View.VISIBLE
                }
            }
        })
    }
}