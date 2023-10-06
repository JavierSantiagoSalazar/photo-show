package com.example.interrapidismotest.page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.not

fun clickViewById(id: Int) {
    onView(withId(id))
        .perform(click())
}

fun typeTextInView(viewId: Int, textToType: String) {
    onView(withId(viewId))
        .perform(typeText(textToType))
        .perform(closeSoftKeyboard())
}

fun verifyItemTextInRecyclerView(recyclerId: Int, text: String) {
    onView(withId(recyclerId))
        .check(
            ViewAssertions.matches(
                hasDescendant(
                    withText(text)
                )
            )
        )
}

fun verifyTextInView(viewId: Int, text: String) {
    onView(withId(viewId))
        .check(ViewAssertions.matches(withText(text)))
}

fun checkIfViewIsNotDisplayed(viewId: Int) {
    onView(withId(viewId))
        .check(ViewAssertions.matches(not(isDisplayed())))
}

fun checkIfViewIsDisplayed(viewId: Int) {
    onView(withId(viewId))
        .check(ViewAssertions.matches(isDisplayed()))
}

fun clickButton(buttonId: Int) {
    onView(withId(buttonId)).perform(click())
}
