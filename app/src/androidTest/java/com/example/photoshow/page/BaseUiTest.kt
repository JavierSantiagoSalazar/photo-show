package com.example.interrapidismotest.page

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.interrapidismotest.ui.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule

open class BaseUiTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    open fun setup() {
        hiltRule.inject()
    }
}
