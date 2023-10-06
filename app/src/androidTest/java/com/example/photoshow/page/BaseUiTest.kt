package com.example.photoshow.page

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.photoshow.ui.NavHostActivity
import com.example.photoshow.ui.splash.SplashScreenActivity
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule

open class BaseUiTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(NavHostActivity::class.java)

    @Before
    open fun setup() {
        hiltRule.inject()
    }
}
