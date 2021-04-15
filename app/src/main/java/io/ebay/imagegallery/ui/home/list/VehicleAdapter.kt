package io.ebay.imagegallery.ui.home.list

import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import io.ebay.imagegallery.data.model.ImageDetail
import io.ebay.imagegallery.ui.base.BaseAdapter

class VehicleAdapter(
    parentLifecycle: Lifecycle,
    vehicleThumbnails : ArrayList<ImageDetail>,
    private val onClick:(ImageDetail,ImageView) -> Unit
) : BaseAdapter<ImageDetail, VehicleItemViewHolder>(parentLifecycle,vehicleThumbnails) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleItemViewHolder = VehicleItemViewHolder(parent,onClick)

}