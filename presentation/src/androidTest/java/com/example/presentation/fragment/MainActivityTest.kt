package com.example.presentation.fragment

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.presentation.MainActivity
import com.example.presentation.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4ClassRunner::class)
internal class MainActivityTest {

    @get: Rule
    val mActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun tabLayout_must_be_visible() {
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun must_be_displayed_All_and_Watchlist_tabs() {
        onView(withText("All")).check(matches(isDisplayed()))
        onView(withText("Watchlist")).check(matches(isDisplayed()))
    }

    @Test
    fun must_be_displayed_sortButton() {
        onView(withId(R.id.sortButton)).check(matches(isDisplayed()))
    }

    @Test
    fun must_be_displayed_AllCountriesFragment() {
        onView(withId(R.id.all_countries_fragment)).check(matches(isDisplayed()))
    }

    @Test
    fun must_be_displayed_WatchedCountriesFragment_after_clicking_on_tab() {
        onView(withId(R.id.all_countries_fragment)).check(matches(isDisplayed()))
        onView(withText("Watchlist")).perform(click())
        onView(withId(R.id.watched_countries_fragment)).check(matches(isDisplayed()))
    }

    @Test
    fun allCountries_recyclerview_must_be_visible() {
        onView(withId(R.id.recyclerViewAll)).check(matches(isDisplayed()))
    }

    @Test
    fun watchedCountries_recyclerview_must_be_visible() {
        onView(withText("Watchlist")).perform(click())
        onView(withId(R.id.recyclerViewWatched)).check(matches(isDisplayed()))
    }

    @Test
    fun watchedCountries_must_navigate_to_CountryDetail_on_item_click() {
        onView(withText("Watchlist")).perform(click())
        onView(withId(R.id.recyclerViewWatched))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.country_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun back_press_must_navigate_back_to_watchedCountries() {
        onView(withText("Watchlist")).perform(click())
        onView(withId(R.id.recyclerViewWatched))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.country_detail)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.recyclerViewWatched)).check(matches(isDisplayed()))
    }

    @Test
    fun allCountries_must_navigate_to_CountryDetail_on_item_click() {
        sleep(1000)
        onView(withId(R.id.recyclerViewAll))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.country_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun back_press_must_navigate_back_to_allCountries() {
        sleep(1000)
        onView(withId(R.id.recyclerViewAll))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.country_detail)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.recyclerViewAll)).check(matches(isDisplayed()))
    }
}