package com.vinaymj.tennisscore.domain

import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class ScoreUpdateUseCaseTest {

    private lateinit var useCase: ScoreUpdateUseCase


    private val match: Match = Match()

    @Before
    fun setUp() {
        useCase = ScoreUpdateUseCase(match)
    }

    @After
    fun tearDown(){
        useCase.resetScoreBoard()
    }


    @Test
    fun `should return game point 1 for player a`() {
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals("15", actual.playerA.gamePoint)
    }

    @Test
    fun `should return game point 2 for player a`() {

        useCase.execute(ScoreUpdateUseCase.PointTo.A)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals("30", actual.playerA.gamePoint)
    }

    @Test
    fun `should return game point 3 for player a`() {
        mockUpdateScore(2, ScoreUpdateUseCase.PointTo.A)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals("40", actual.playerA.gamePoint)
    }

    @Test
    fun `should return game point 0 for player a`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.A)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals("0", actual.playerA.gamePoint)
    }

    @Test
    fun `should return deuce true when player A point increased`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.B)
        mockUpdateScore(2, ScoreUpdateUseCase.PointTo.A)
        useCase.execute(ScoreUpdateUseCase.PointTo.A)
        Assert.assertTrue(match.deuce)
    }

    @Test
    fun `should return game point 15 for player b`() {
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.B)
        Assert.assertEquals("15", actual.playerB.gamePoint)
    }

    @Test
    fun `should return game point 30 for player b`() {
        mockUpdateScore(1, ScoreUpdateUseCase.PointTo.B)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.B)
        Assert.assertEquals("30", actual.playerB.gamePoint)
    }

    @Test
    fun `should return game point 40 for player b`() {
        mockUpdateScore(2, ScoreUpdateUseCase.PointTo.B)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.B)
        Assert.assertEquals("40", actual.playerB.gamePoint)
    }

    @Test
    fun `should return game point 0 when set point is 1 for player b`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.B)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.B)
        Assert.assertEquals("0", actual.playerB.gamePoint)
        Assert.assertEquals(1, actual.playerB.setPoint)
    }

    @Test
    fun `should return deuce true when player B point increased`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.A)
        mockUpdateScore(2, ScoreUpdateUseCase.PointTo.B)
        useCase.execute(ScoreUpdateUseCase.PointTo.B)
        Assert.assertTrue(match.deuce)
    }

    @Test
    fun `should return "AD" when deuce true and player A point increased`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.B)
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.A)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals("AD", actual.playerA.gamePoint)
    }

    @Test
    fun `should return "AD" when deuce true and player B point increased`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.B)
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.A)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.B)
        Assert.assertEquals("AD", actual.playerB.gamePoint)
    }

    @Test
    fun `should return 0 when deuce is true and difference of player A - B point is 2`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.B)
        mockUpdateScore(4, ScoreUpdateUseCase.PointTo.A)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals("0", actual.playerA.gamePoint)
        Assert.assertEquals("0", actual.playerB.gamePoint)
        Assert.assertEquals(1, actual.playerA.setPoint)
    }

    @Test
    fun `should return 0 when deuce is true and difference of player B - A point is 2`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.A)
        mockUpdateScore(4, ScoreUpdateUseCase.PointTo.B)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.B)
        Assert.assertEquals("0", actual.playerA.gamePoint)
        Assert.assertEquals("0", actual.playerB.gamePoint)
        Assert.assertEquals(1, actual.playerB.setPoint)
    }

    @Test
    fun `should return 40 when you increase player A point and deuce again`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.A)
        mockUpdateScore(4, ScoreUpdateUseCase.PointTo.B)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals("40", actual.playerB.gamePoint)
        Assert.assertEquals("40", actual.playerA.gamePoint)
    }

    @Test
    fun `should return 40 when you increase player B point and deuce again`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.B)
        mockUpdateScore(4, ScoreUpdateUseCase.PointTo.A)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.B)
        Assert.assertEquals("40", actual.playerB.gamePoint)
        Assert.assertEquals("40", actual.playerA.gamePoint)
    }


    @Test
    fun `should return set point 1 when player A is game`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.A)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals(1, actual.playerA.setPoint)
        Assert.assertEquals("0", actual.playerA.gamePoint)
        Assert.assertEquals("0", actual.playerB.gamePoint)
    }

    @Test
    fun `should return set point 2 when player A is game again`() {
        mockUpdateScore(7, ScoreUpdateUseCase.PointTo.A)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals(2, actual.playerA.setPoint)
        Assert.assertEquals("0", actual.playerA.gamePoint)
        Assert.assertEquals("0", actual.playerB.gamePoint)
    }

    @Test
    fun `should return set point 0 when player A set point reaches to 6`() {
        mockUpdateScore(23, ScoreUpdateUseCase.PointTo.A)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals(0, actual.playerA.setPoint)
        Assert.assertEquals("0", actual.playerA.gamePoint)
        Assert.assertEquals("0", actual.playerB.gamePoint)
    }

    @Test
    fun `should return match point 1 when player A set point reaches to 6`() {
        mockUpdateScore(23, ScoreUpdateUseCase.PointTo.A)
        val actual = useCase.execute( ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals(1, actual.playerA.matchPoint)
        Assert.assertEquals(0, actual.playerA.setPoint)
        Assert.assertEquals("0", actual.playerA.gamePoint)
        Assert.assertEquals("0", actual.playerB.gamePoint)
        Assert.assertEquals(0, actual.playerB.setPoint)
    }

    @Test
    fun `should not update the score after match win`() {
        mockUpdateScore(24, ScoreUpdateUseCase.PointTo.A)
        mockUpdateScore(24, ScoreUpdateUseCase.PointTo.A)
        val actual = useCase.execute(ScoreUpdateUseCase.PointTo.A)
        Assert.assertEquals(2, actual.playerA.matchPoint)
        Assert.assertEquals(0, actual.playerA.setPoint)
        Assert.assertEquals("0", actual.playerA.gamePoint)
        Assert.assertEquals("0", actual.playerB.gamePoint)
        Assert.assertEquals(0, actual.playerB.setPoint)
        Assert.assertEquals(true, actual.someBodyWin)
    }

    @Test
    fun `reset score board`() {
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.A)
        mockUpdateScore(3, ScoreUpdateUseCase.PointTo.B)
        val actual = useCase.resetScoreBoard()
        Assert.assertEquals("0", actual.playerA.gamePoint)
        Assert.assertEquals(0, actual.playerA.setPoint)
        Assert.assertEquals(0, actual.playerA.matchPoint)
        Assert.assertEquals("0", actual.playerB.gamePoint)
        Assert.assertEquals(0, actual.playerB.setPoint)
        Assert.assertEquals(0, actual.playerB.matchPoint)
        Assert.assertEquals(ScoreUpdateUseCase.PointTo.NON, actual.pointTo)
        Assert.assertEquals(false, actual.someBodyWin)
    }

    private fun mockUpdateScore(count: Int, pointTo: ScoreUpdateUseCase.PointTo) {
        for(i in 1..count) {
            useCase.execute(pointTo)
        }
    }
}