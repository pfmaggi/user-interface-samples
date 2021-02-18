package com.example.windowmanagersample

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule
import com.example.windowmanagersample.BaseSampleActivity.Companion.BACKEND_TYPE_EXTRA
import com.example.windowmanagersample.BaseSampleActivity.Companion.BACKEND_TYPE_SHORT_DIMENSION_FOLD

@RunWith(AndroidJUnit4::class)
class DisplayFeaturesActivityTest{
    @get:Rule
    val mActivityTestRule: ActivityTestRule<DisplayFeaturesActivity> =
        object : ActivityTestRule<DisplayFeaturesActivity>(DisplayFeaturesActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, DisplayFeaturesActivity::class.java).apply {
                    putExtra(BACKEND_TYPE_EXTRA, BACKEND_TYPE_SHORT_DIMENSION_FOLD)
                }
            }
        }

    @Test
    fun foldingAndUnfolding_UpdateStateInUi() {
        onView(withId(R.id.current_state)).check(matches(withSubstring("state=FLAT")))
        onView(withId(R.id.device_state_toggle_button)).perform(click())
        onView(withId(R.id.current_state)).check(matches(withSubstring("state=HALF_OPENED")))
        onView(withId(R.id.device_state_toggle_button)).perform(click())
        onView(withId(R.id.current_state)).check(matches(withSubstring("state=FLAT")))
    }
}