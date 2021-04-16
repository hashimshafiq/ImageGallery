package io.ebay.imagegallery.data.remote

import io.ebay.imagegallery.data.remote.response.VehicleDetailResponse
import io.ebay.imagegallery.utils.FakeData
import io.reactivex.Single

class FakeNetworkService : NetworkService {

    override fun doFetchVehicleDetails(): Single<VehicleDetailResponse> {
        return  Single.just(FakeData.getFakeData())
    }
}