package com.vinaymj.tennisscore.domain

import org.junit.Assert
import org.junit.Before
import org.junit.Test


class MatchTest {


    @Before
    fun setUp() {
        Match.resetMatchScore()
    }


    @Test
    fun `should return initial score is 0`() {
        Assert.assertEquals(0, Match.playerA.score.gamePoint)
        Assert.assertEquals(0, Match.playerA.score.setPoint)
        Assert.assertEquals(0, Match.playerA.score.matchPoint)
        Assert.assertEquals(0, Match.playerB.score.gamePoint)
        Assert.assertEquals(0, Match.playerB.score.setPoint)
        Assert.assertEquals(0, Match.playerB.score.matchPoint)
        Assert.assertEquals(false, Match.deuce)
        Assert.assertEquals(ScoreUpdateUseCase.PointTo.NON, Match.pointTo)
    }

    @Test
    fun `should return game point 1 for team A`() {
        Match.updateScore(ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals(1, Match.playerA.score.gamePoint)
    }

    @Test
    fun `should return set point 1 for team A`() {
        mockUpdateScore(4, ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals(1, Match.playerA.score.setPoint)
    }

    @Test
    fun `should return match point 1 for team A`() {
        mockUpdateScore(24, ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals(1, Match.playerA.score.matchPoint)
    }

    @Test
    fun `should return game point 1 for team B`() {
        Match.updateScore(ScoreUpdateUseCase.PointTo.B)
        Assert.assertEquals(1, Match.playerB.score.gamePoint)
    }

    @Test
    fun `should return set point 1 for team B`() {
        mockUpdateScore(4, ScoreUpdateUseCase.PointTo.B)
        Assert.assertEquals(1, Match.playerB.score.setPoint)
    }

    @Test
    fun `should return match point 1 for team B`() {
        mockUpdateScore(24, ScoreUpdateUseCase.PointTo.B)
        Assert.assertEquals(1, Match.playerB.score.matchPoint)
    }

    @Test
    fun `should return deuce true`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.B)
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.A)
        Assert.assertTrue(Match.deuce)
    }

    @Test
    fun `should return game point 4 for team A when deuce is true`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.B)
        mockUpdateScore(4, ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals(4, Match.playerA.score.gamePoint)
    }

    @Test
    fun `should return set point 1 for team A when deuce is true and difference between A and B is 2`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.B)
        mockUpdateScore(5, ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals(1, Match.playerA.score.setPoint)
    }

    private fun mockUpdateScore(count: Int, pointTo: ScoreUpdateUseCase.PointTo) {
        for(i in 1..count) {
            Match.updateScore(pointTo)
        }
    }

}