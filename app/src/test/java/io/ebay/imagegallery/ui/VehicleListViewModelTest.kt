package io.ebay.imagegallery.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.ebay.imagegallery.data.model.ImageDetail
import io.ebay.imagegallery.data.remote.response.VehicleDetailResponse
import io.ebay.imagegallery.data.repository.VehicleRepository
import io.ebay.imagegallery.ui.home.VehicleListViewModel
import io.ebay.imagegallery.utils.TestSchedulerProvider
import io.ebay.imagegallery.utils.common.Event
import io.ebay.imagegallery.utils.common.Resource
import io.ebay.imagegallery.utils.network.NetworkHelper
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class VehicleListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var vehicleRepository: VehicleRepository

    @Mock
    private lateinit var messagingStringIdObserver : Observer<Resource<Int>>

    @Mock
    private lateinit var detailImage : Observer<Resource<List<ImageDetail>>>

    @Mock
    private lateinit var noInternetLayout : Observer<Event<Boolean>>

    private lateinit var testScheduler: TestScheduler

    private lateinit var vehicleListViewModel: VehicleListViewModel

    @Before
    fun setup(){
        val compositeDisposable = CompositeDisposable()

        testScheduler = TestScheduler()
        val testSchedulerProvider =
                TestSchedulerProvider(testScheduler)

        vehicleListViewModel = VehicleListViewModel(
                testSchedulerProvider,
                compositeDisposable,
                networkHelper,
                vehicleRepository
        )

        vehicleListViewModel.messageStringId.observeForever(messagingStringIdObserver)
        vehicleListViewModel.noInternetLayout.observeForever(noInternetLayout)
        vehicleListViewModel.detailImageLiveData.observeForever(detailImage)
    }

    @Test
    fun givenNoInternet_whenFetchList_shouldShowNoNetworkLayout(){


        doReturn(false)
                .`when`(networkHelper)
                .isNetworkConnected()

        vehicleListViewModel.fetchVehicleDetails()

        verify(noInternetLayout).onChanged(Event(true))
    }

    @Test
    fun givenServerResponse200_whenFetchList_shouldReturnVehicleImagesList(){


        val fakeData = VehicleDetailResponse(listOf())
        val fakeList = listOf<ImageDetail>()

        doReturn(true)
            .`when`(networkHelper)
            .isNetworkConnected()


        doReturn(Single.just(fakeData))
            .`when`(vehicleRepository)
            .fetchCarDetails()

        vehicleListViewModel.fetchVehicleDetails()

        testScheduler.triggerActions()


        assert(vehicleListViewModel.detailImageLiveData.value == Resource.success(fakeList))
        verify(noInternetLayout).onChanged(Event(false))
        verify(detailImage).onChanged(Resource.loading())
        verify(detailImage).onChanged(Resource.success(fakeList))
    }



    @After
    fun tearDown(){
        vehicleListViewModel.messageStringId.removeObserver(messagingStringIdObserver)
        vehicleListViewModel.noInternetLayout.removeObserver(noInternetLayout)
        vehicleListViewModel.detailImageLiveData.removeObserver(detailImage)
    }
}