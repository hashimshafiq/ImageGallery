package io.ebay.imagegallery.data.remote

import io.ebay.imagegallery.data.remote.response.VehicleDetailResponse
import io.reactivex.Single
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(Endpoints.FETCH_VEHICLE_DETAIL)
    fun doFetchVehicleDetails() : Single<VehicleDetailResponse>

}