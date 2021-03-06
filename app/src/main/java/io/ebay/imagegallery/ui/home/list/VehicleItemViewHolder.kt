package io.ebay.imagegallery.ui.home.list

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import io.ebay.imagegallery.R
import io.ebay.imagegallery.data.model.ImageDetail
import io.ebay.imagegallery.databinding.CustomImageRowBinding
import io.ebay.imagegallery.di.component.ViewHolderComponent
import io.ebay.imagegallery.ui.base.BaseItemViewHolder
import javax.inject.Inject

class VehicleItemViewHolder(parent : ViewGroup, private val onClick: (ImageDetail, ImageView) -> Unit) : BaseItemViewHolder<ImageDetail, VehicleItemViewModel>(
    R.layout.custom_image_row,parent) {

    @Inject
    lateinit var circularProgressDrawable: CircularProgressDrawable

    private lateinit var binding: CustomImageRowBinding

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(view: View) {
       binding  = CustomImageRowBinding.bind(view)


        viewModel.imageURL.observe(this, {
            it?.run {
                Glide
                    .with(binding.vehicleThumbnail.context)
                    .load(this)
                    .placeholder(circularProgressDrawable)
                    .into(binding.vehicleThumbnail)
            }
        })

        itemView.setOnClickListener {
            onClick(viewModel.data.value!!,binding.vehicleThumbnail)
        }


    }






}