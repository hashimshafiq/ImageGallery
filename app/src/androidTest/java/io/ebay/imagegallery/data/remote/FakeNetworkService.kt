package io.ebay.imagegallery.data.remote

import io.ebay.imagegallery.data.remote.response.VehicleDetailResponse
import io.reactivex.Single

class FakeNetworkService : NetworkService {

    override fun doFetchVehicleDetails(): Single<VehicleDetailResponse> {

        val vehicleDetailResponse = VehicleDetailResponse(listOf(
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/8B4AAOSwLHhfTKzk/$","fake"),
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/kZ8AAOSw~bBfTKzj/$","fake"),
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/rNoAAOSw74BfTKzu/$","fake"),
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/HewAAOSwFtFfTKzk/$","fake")
        ))

        return  Single.just(vehicleDetailResponse)
    }
}