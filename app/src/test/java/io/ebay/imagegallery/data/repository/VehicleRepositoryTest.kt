package io.ebay.imagegallery.data.repository

import io.ebay.imagegallery.data.remote.NetworkService
import io.ebay.imagegallery.data.remote.response.VehicleDetailResponse
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class VehicleRepositoryTest {

    @Mock
    lateinit var networkService: NetworkService

    lateinit var vehicleRepository: VehicleRepository

    @Before
    fun setup(){
        vehicleRepository = VehicleRepository(networkService)
    }

    @Test
    fun givenServerResponse200_whenFetchVehicles_shouldReturnListOfImagesOfVehicle(){

        val list = listOf<VehicleDetailResponse>()

        doReturn(Single.just(list))
            .`when`(networkService)
            .doFetchVehicleDetails()

        vehicleRepository.fetchCarDetails()

        verify(networkService).doFetchVehicleDetails()
    }

    @After
    fun tearDown(){

    }
}