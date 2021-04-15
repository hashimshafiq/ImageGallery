package io.ebay.imagegallery.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VehicleDetailResponse(

    @Expose
    @SerializedName("images")
    val images: List<VehicleImage>

) {
    data class VehicleImage(

        @Expose
        @SerializedName("uri")
        val uri: String,

        @Expose
        @SerializedName("set")
        val set: String
    )
}