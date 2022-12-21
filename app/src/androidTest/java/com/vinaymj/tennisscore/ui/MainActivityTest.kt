package com.vinaymj.tennisscore.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.vinaymj.tennisscore.ui.theme.TennisScoreBoardTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp(){
        composeTestRule.setContent {
            TennisScoreBoardTheme {
                ScoreBoardScreen()
            }
        }
    }

    @Test
    fun shouldDisplayScoreTable() {
        composeTestRule.onNodeWithTag("rowHeadPlayerA").assertIsDisplayed()
        composeTestRule.onNodeWithTag("rowHeadPlayerB").assertIsDisplayed()
        composeTestRule.onNodeWithText("Match").assertIsDisplayed()
        composeTestRule.onNodeWithText("Set").assertIsDisplayed()
        composeTestRule.onNodeWithText("Point").assertIsDisplayed()
    }

    @Test
    fun shouldDisplayPlayerAGamePointOne() {
        composeTestRule.onNodeWithTag("playerAButton").performClick()
        composeTestRule.onNodeWithTag("playerAGamePoint").assertTextEquals("15")
    }

    @Test
    fun shouldDisplayPlayerASetPointOne() {

        performOneFullSet(composeTestRule.onNodeWithTag("playerAButton"))

        composeTestRule.onNodeWithTag("playerASetPoint").assertTextEquals("1")
    }

    @Test
    fun shouldDisplayPlayerAMatchPointOne() {

        performOneFullMatch(composeTestRule.onNodeWithTag("playerAButton"))

        composeTestRule.onNodeWithTag("playerAMatchPoint").assertTextEquals("1")
    }

    @Test
    fun shouldDisplayPlayerAWin() {

        performOneFullMatch(composeTestRule.onNodeWithTag("playerAButton"))
        performOneFullMatch(composeTestRule.onNodeWithTag("playerAButton"))

        composeTestRule.onNodeWithTag("matchResult").assertTextEquals("Player A won the match..!")
    }

    @Test
    fun shouldDisplayPlayerBGamePointOne() {
        composeTestRule.onNodeWithTag("playerBButton").performClick()
        composeTestRule.onNodeWithTag("playerBGamePoint").assertTextEquals("15")
    }

    @Test
    fun shouldDisplayPlayerBSetPointOne() {

        performOneFullSet(composeTestRule.onNodeWithTag("playerBButton"))

        composeTestRule.onNodeWithTag("playerBSetPoint").assertTextEquals("1")
    }

    @Test
    fun shouldDisplayPlayerBMatchPointOne() {

        performOneFullMatch(composeTestRule.onNodeWithTag("playerBButton"))

        composeTestRule.onNodeWithTag("playerBMatchPoint").assertTextEquals("1")
    }

    @Test
    fun shouldDisplayPlayerBWin() {

        performOneFullMatch(composeTestRule.onNodeWithTag("playerBButton"))
        performOneFullMatch(composeTestRule.onNodeWithTag("playerBButton"))

        composeTestRule.onNodeWithTag("matchResult").assertTextEquals("Player B won the match..!")
    }

    @Test
    fun shouldDisplayABForPlayerAWhenDeuce() {

        performDeuce(composeTestRule.onNodeWithTag("playerAButton"),
            composeTestRule.onNodeWithTag("playerBButton"))

        composeTestRule.onNodeWithTag("playerAButton").performClick()

        composeTestRule.onNodeWithTag("playerAGamePoint").assertTextEquals("AD")
    }

    @Test
    fun shouldDisplayABForPlayerBWhenDeuce() {

        performDeuce(composeTestRule.onNodeWithTag("playerAButton"),
            composeTestRule.onNodeWithTag("playerBButton"))

        composeTestRule.onNodeWithTag("playerBButton").performClick()

        composeTestRule.onNodeWithTag("playerBGamePoint").assertTextEquals("AD")
    }

    @Test
    fun playerAShouldWinWhenDeuce() {

        performDeuce(composeTestRule.onNodeWithTag("playerAButton"),
            composeTestRule.onNodeWithTag("playerBButton"))

        composeTestRule.onNodeWithTag("playerAButton").performClick()
        composeTestRule.onNodeWithTag("playerAButton").performClick()

        composeTestRule.onNodeWithTag("playerASetPoint").assertTextEquals("1")
    }

    @Test
    fun shouldDisplayZerosOnClickResetButton() {

        performOneFullMatch(composeTestRule.onNodeWithTag("playerAButton"))
        performOneFullMatch(composeTestRule.onNodeWithTag("playerBButton"))

        performOneFullSet(composeTestRule.onNodeWithTag("playerAButton"))
        performOneFullSet(composeTestRule.onNodeWithTag("playerBButton"))

        composeTestRule.onNodeWithTag("playerAButton").performClick()
        composeTestRule.onNodeWithTag("playerBButton").performClick()

        composeTestRule.onNodeWithTag("resetButton").performClick()

        composeTestRule.onNodeWithTag("playerAGamePoint").assertTextEquals("0")
        composeTestRule.onNodeWithTag("playerBGamePoint").assertTextEquals("0")
        composeTestRule.onNodeWithTag("playerASetPoint").assertTextEquals("0")
        composeTestRule.onNodeWithTag("playerBSetPoint").assertTextEquals("0")
        composeTestRule.onNodeWithTag("playerAMatchPoint").assertTextEquals("0")
        composeTestRule.onNodeWithTag("playerBMatchPoint").assertTextEquals("0")
    }

}