package com.altran.towncodex

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import com.altran.towncodex.main.InhabitantsListAdapter
import com.altran.towncodex.main.MainActivity
import org.junit.Rule
import org.junit.Test

class MainActivityInstrumentedTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testRecyclerViewIsPopulated() {

        Espresso.onView(ViewMatchers.withId(R.id.rv_inhabitants_list_activity_main))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Age: 306"))))
    }

    @Test
    fun testRecyclerViewItemClickLaunchesDetailActivity() {

        Espresso.onView(ViewMatchers.withId(R.id.rv_inhabitants_list_activity_main))
                .perform(RecyclerViewActions.scrollToPosition<InhabitantsListAdapter.ViewHolder>(2))
                .perform(RecyclerViewActions.actionOnItemAtPosition<InhabitantsListAdapter.ViewHolder>(2, ViewActions.click()))

        assert(Espresso.onView(ViewMatchers.withId(R.id.rv_inhabitants_list_activity_main)) == null)
    }
}
