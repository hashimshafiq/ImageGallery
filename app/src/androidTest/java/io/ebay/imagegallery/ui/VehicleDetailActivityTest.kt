package io.ebay.imagegallery.ui

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import io.ebay.imagegallery.R
import io.ebay.imagegallery.TestComponentRule
import io.ebay.imagegallery.data.model.ImageDetail
import io.ebay.imagegallery.ui.detail.VehicleDetailActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

class VehicleDetailActivityTest {

    private val component = TestComponentRule(InstrumentationRegistry.getInstrumentation().targetContext)

    private val main = IntentsTestRule(VehicleDetailActivity::class.java,false,false)

    @get:Rule
    val chain: RuleChain = RuleChain.outerRule(component).around(main)

    @Before
    fun setup(){}

    @Test
    fun givenVehicleDetailActivity_whenActivityDisplayed_shouldVisibleAllWidgets(){
        main.launchActivity(Intent(component.getContext(),VehicleDetailActivity::class.java).apply {
            putExtra("data",ImageDetail("https://i.ebayimg.com/00/s/MTA2N1gxNjAw/z/8B4AAOSwLHhfTKzk/\$_2.jpg","https://i.ebayimg.com/00/s/MTA2N1gxNjAw/z/8B4AAOSwLHhfTKzk/\$_27.jpg"))
        })
        onView(withId(R.id.btnBack)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.heading)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.ivHighRes)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @After
    fun tearDown(){}
}