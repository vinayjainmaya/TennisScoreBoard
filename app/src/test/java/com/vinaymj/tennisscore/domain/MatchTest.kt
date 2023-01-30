package com.vinaymj.tennisscore.domain

import org.junit.Assert
import org.junit.Before
import org.junit.Test


class MatchTest {

    private val match: Match = Match()

    @Before
    fun setUp() {
        match.resetMatchScore()
    }


    @Test
    fun `should return initial score is 0`() {
        Assert.assertEquals(0, match.playerA.score.gamePoint)
        Assert.assertEquals(0, match.playerA.score.setPoint)
        Assert.assertEquals(0, match.playerA.score.matchPoint)
        Assert.assertEquals(0, match.playerB.score.gamePoint)
        Assert.assertEquals(0, match.playerB.score.setPoint)
        Assert.assertEquals(0, match.playerB.score.matchPoint)
        Assert.assertEquals(false, match.deuce)
        Assert.assertEquals(ScoreUpdateUseCase.PointTo.NON, match.pointTo)
    }

    @Test
    fun `should return game point 1 for team A`() {
        match.updateScore(ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals(1, match.playerA.score.gamePoint)
    }

    @Test
    fun `should return set point 1 for team A`() {
        mockUpdateScore(4, ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals(1, match.playerA.score.setPoint)
    }

    @Test
    fun `should return match point 1 for team A`() {
        mockUpdateScore(24, ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals(1, match.playerA.score.matchPoint)
    }

    @Test
    fun `should return game point 1 for team B`() {
        match.updateScore(ScoreUpdateUseCase.PointTo.B)
        Assert.assertEquals(1, match.playerB.score.gamePoint)
    }

    @Test
    fun `should return set point 1 for team B`() {
        mockUpdateScore(4, ScoreUpdateUseCase.PointTo.B)
        Assert.assertEquals(1, match.playerB.score.setPoint)
    }

    @Test
    fun `should return match point 1 for team B`() {
        mockUpdateScore(24, ScoreUpdateUseCase.PointTo.B)
        Assert.assertEquals(1, match.playerB.score.matchPoint)
    }

    @Test
    fun `should return deuce true`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.B)
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.A)
        Assert.assertTrue(match.deuce)
    }

    @Test
    fun `should return game point 4 for team A when deuce is true`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.B)
        mockUpdateScore(4, ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals(4, match.playerA.score.gamePoint)
    }

    @Test
    fun `should return set point 1 for team A when deuce is true and difference between A and B is 2`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.B)
        mockUpdateScore(5, ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals(1, match.playerA.score.setPoint)
    }

    @Test
    fun `match return matchScore value when you call toMatchScore`() {
        val matchScore = match.toMatchScore()
        Assert.assertEquals("0", matchScore.playerAScore.gamePoint)
        Assert.assertEquals(0, matchScore.playerAScore.setPoint)
        Assert.assertEquals(0, matchScore.playerAScore.matchPoint)
        Assert.assertEquals("0", matchScore.playerBScore.gamePoint)
        Assert.assertEquals(0, matchScore.playerBScore.setPoint)
        Assert.assertEquals(0, matchScore.playerBScore.matchPoint)
    }

    private fun mockUpdateScore(count: Int, pointTo: ScoreUpdateUseCase.PointTo) {
        for(i in 1..count) {
            match.updateScore(pointTo)
        }
    }

}