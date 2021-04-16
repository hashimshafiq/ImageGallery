package io.ebay.imagegallery.utils

import io.ebay.imagegallery.data.remote.response.VehicleDetailResponse

object FakeData {

    fun getFakeData() : VehicleDetailResponse {
        return VehicleDetailResponse(listOf(
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/8B4AAOSwLHhfTKzk/$","fake"),
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/kZ8AAOSw~bBfTKzj/$","fake"),
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/rNoAAOSw74BfTKzu/$","fake"),
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/HewAAOSwFtFfTKzk/$","fake"),
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/8B4AAOSwLHhfTKzk/$","fake"),
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/kZ8AAOSw~bBfTKzj/$","fake"),
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/rNoAAOSw74BfTKzu/$","fake"),
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/HewAAOSwFtFfTKzk/$","fake"),
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/8B4AAOSwLHhfTKzk/$","fake"),
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/kZ8AAOSw~bBfTKzj/$","fake"),
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/rNoAAOSw74BfTKzu/$","fake"),
                VehicleDetailResponse.VehicleImage("i.ebayimg.com/00/s/MTA2N1gxNjAw/z/HewAAOSwFtFfTKzk/$","fake")

        ))
    }
}