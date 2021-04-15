package io.ebay.imagegallery.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageDetail(

    var thumbnailURL: String,
    var highResURL: String

) : Parcelable