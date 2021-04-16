package io.ebay.imagegallery.ui

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import io.ebay.imagegallery.R
import io.ebay.imagegallery.TestComponentRule
import io.ebay.imagegallery.ui.detail.VehicleDetailActivity
import io.ebay.imagegallery.ui.home.VehicleListActivity
import io.ebay.imagegallery.ui.home.list.VehicleItemViewHolder
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

class VehicleListActivityTest {

    companion object {
        const val LIST_ITEM_IN_TEST = 7
    }

    private val component = TestComponentRule(InstrumentationRegistry.getInstrumentation().targetContext)

    private val main = IntentsTestRule(VehicleListActivity::class.java,false,false)

    @get:Rule
    val rule: RuleChain = RuleChain.outerRule(component).around(main)

    private lateinit var intent: Intent

    @Before
    fun setup(){
        intent = Intent(component.getContext(), VehicleListActivity::class.java)
    }

    @Test
    fun givenAccessCardFragment_whenDisplayed_shouldVisibleAllWidgets(){
        main.launchActivity(intent)
        onView(withId(R.id.heading)).check(matches(isDisplayed()))
        onView(withId(R.id.rvVehicleThumbnails)).check(matches(isDisplayed()))
        onView(withId(R.id.noInternetLayout)).check(matches(not(isDisplayed())))

    }

    @Test
    fun givenAccessCardFragment_whenDisplayed_shouldHaveRightTexts(){
        main.launchActivity(intent)
        onView(withId(R.id.heading)).check(matches(withText(R.string.app_name)))
    }

    @Test
    fun fetchAccessCards_whenDisplayed_ClickOnItem(){
        main.launchActivity(intent)
        onView(withId(R.id.rvVehicleThumbnails)).perform(actionOnItemAtPosition<VehicleItemViewHolder>(LIST_ITEM_IN_TEST, click()))
        intended(hasComponent(VehicleDetailActivity::class.java.name))
        onView(withId(R.id.heading)).check(matches(withText(R.string.detail)))
    }

    @Test
    fun givenDetailVehicle_whenDisplayed_ClickBack(){
        main.launchActivity(intent)
        onView(withId(R.id.rvVehicleThumbnails)).perform(actionOnItemAtPosition<VehicleItemViewHolder>(LIST_ITEM_IN_TEST, click()))
        intended(hasComponent(VehicleDetailActivity::class.java.name))
        onView(withId(R.id.heading)).check(matches(withText(R.string.detail)))

        pressBack()

        onView(withId(R.id.heading)).check(matches(withText(R.string.app_name)))
    }



    @After
    fun tearDown(){

    }
}