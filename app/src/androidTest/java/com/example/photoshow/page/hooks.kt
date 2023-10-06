package com.example.photoshow.page

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.not

fun verifyItemTextInRecyclerView(recyclerId: Int, text: String) {
    onView(withId(recyclerId))
        .check(
            matches(
                hasDescendant(
                    withText(text)
                )
            )
        )
}

fun clickInRecyclerItem(recyclerId: Int, itemPosition: Int) {
    onView(withId(recyclerId))
        .perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(itemPosition,
                click()
            )
        )
}

fun verifyTextInView(viewId: Int, text: String) {
    onView(withId(viewId))
        .check(matches(withText(text)))
}


fun verifyTextInChild(parentId: Int, text: String) {
    onView(withId(parentId))
        .check(matches(hasDescendant(withText(text))))
}


fun clickButton(buttonId: Int) {
    onView(withId(buttonId)).perform(click())
}

fun clickOnText(text: String) {
    onView(withText(text)).perform(click())
}

fun verifyTextIsNotDisplayed(photoTitle: String) {
    onView(withText(photoTitle))
        .check(ViewAssertions.doesNotExist());
}
