package com.plcoding.jetpackcomposepokedex.splash

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import com.plcoding.jetpackcomposepokedex.MainActivity
import com.plcoding.jetpackcomposepokedex.ui.theme.JetpackComposePokedexTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SplashActivityTest {

    @get:Rule(order = 1)
        val composeTestRule = createAndroidComposeRule<SplashActivity>()

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun TestLogoApear() {
        composeTestRule.onNode(hasTestTag("Logo"), true)
            .assertIsEnabled()
            .assertIsDisplayed()
    }

    @Test
    fun TestCircularLoadingApear() {
        composeTestRule.onNode(hasTestTag("CircularLoading"), true)
            .assertIsEnabled()
            .assertIsDisplayed()
    }

    @Test
    fun TestBackGround() {
        composeTestRule.onNode(hasTestTag("BackGround"), true)
            .assertIsEnabled()
            .assertIsDisplayed()
    }

}