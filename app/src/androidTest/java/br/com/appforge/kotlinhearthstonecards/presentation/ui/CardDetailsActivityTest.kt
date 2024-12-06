package br.com.appforge.kotlinhearthstonecards.presentation.ui

import CardGalleryAdapter
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import br.com.appforge.kotlinhearthstonecards.R

import org.junit.Rule
import org.junit.Test


class CardDetailsActivityTest{

    @get:Rule
    val scenarioRule = ActivityScenarioRule(SetsActivity::class.java)

    @Test
    fun galleryActivity_openCardDetails_onItemClick(){

        onView(withId(R.id.rvCardSets))
            .perform(waitForRecyclerViewPopulation())
            .check(matches(hasMinimumChildCount(1)))

        onView(withId(R.id.rvCardSets))
            .perform(actionOnItemAtPosition<CardGalleryAdapter.CardItemViewHolder>(1,click()))

        onView(withId(R.id.rvCards))
            .perform(waitForRecyclerViewPopulation())
            .check(matches(hasMinimumChildCount(1)))

        onView(withId(R.id.rvCards))
            .perform(actionOnItemAtPosition<CardGalleryAdapter.CardItemViewHolder>(2,click()))

        onView(withId(R.id.cardNameText))
            .check(matches(isDisplayed()))

    }

    private fun waitForRecyclerViewPopulation(): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "Wait for recyclerView data be loaded."

            override fun getConstraints() = isAssignableFrom(RecyclerView::class.java)

            override fun perform(uiController: UiController, view: View) {
                val recyclerView = view as RecyclerView
                uiController.loopMainThreadUntilIdle()
                while (recyclerView.adapter?.itemCount == 0) {
                    uiController.loopMainThreadForAtLeast(50)
                }
            }
        }
    }


}