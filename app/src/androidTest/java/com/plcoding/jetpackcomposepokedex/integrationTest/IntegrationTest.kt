package com.plcoding.jetpackcomposepokedex.integrationTest

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.plcoding.jetpackcomposepokedex.splash.SplashActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class IntegrationTest {

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<SplashActivity>()

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun IntegrationTestDitto() {
        composeTestRule.waitUntil(1000000) {
            composeTestRule.onAllNodesWithTag("BoxClick1").fetchSemanticsNodes().size == 1
        }
        composeTestRule.onNode(hasTestTag("SearchBar"), true).performClick()
        composeTestRule.onNode(hasTestTag("SearchBar"), true).performTextInput("ditto")
        composeTestRule.onNode(hasTestTag("SearchBar"), true).performTextClearance()
        composeTestRule.onNode(hasTestTag("SearchBar"), true).performTextInput("ditto")
        composeTestRule.onNode(hasTestTag("BoxClick132"), true)
            .performClick()
        composeTestRule.waitUntil(1000000) {
            composeTestRule.onAllNodesWithTag("ImagePokemon").fetchSemanticsNodes().size == 1
        }
        composeTestRule.onNode(hasTestTag("PokemonName"))
            .assertTextEquals("#132 Ditto")
    }

    @Test
    fun IntegrationTestBulbasaur() {
        composeTestRule.waitUntil(1000000) {
            composeTestRule.onAllNodesWithTag("BoxClick1").fetchSemanticsNodes().size == 1
        }
        composeTestRule.onNode(hasTestTag("SearchBar"), true).performClick()
        composeTestRule.onNode(hasTestTag("SearchBar"), true).performTextInput("bulbasaur")
        composeTestRule.onNode(hasTestTag("SearchBar"), true).performTextClearance()
        composeTestRule.onNode(hasTestTag("SearchBar"), true).performTextInput("bulbasaur")

        composeTestRule.onNode(hasTestTag("BoxClick1"), true)
            .performClick()
        composeTestRule.waitUntil(1000000) {
            composeTestRule.onAllNodesWithTag("ImagePokemon").fetchSemanticsNodes().size == 1
        }
        composeTestRule.onNode(hasTestTag("PokemonName"))
            .assertTextEquals("#1 Bulbasaur")

    }


}
