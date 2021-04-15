package io.ebay.imagegallery.data.repository


import io.ebay.imagegallery.data.remote.NetworkService
import io.ebay.imagegallery.data.remote.response.VehicleDetailResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun fetchCarDetails() : Single<VehicleDetailResponse> {
        return networkService.doFetchVehicleDetails()
    }

}