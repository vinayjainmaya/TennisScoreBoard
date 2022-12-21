package com.vinaymj.tennisscore.domain

import com.vinaymj.tennisscore.getMatchMockObject
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class ScoreUpdateUseCaseTest {

    private lateinit var useCase: ScoreUpdateUseCase
    @Before
    fun setUp() {
        useCase = ScoreUpdateUseCase()
    }


    @Test
    fun `should return game point 1 for player a`() {
        val mockObject = getMatchMockObject()
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(1, actual.playerA.score.gamePoint)
    }

    @Test
    fun `should return game point 2 for player a`() {
        val mockObject = getMatchMockObject(
            1,0,0,
            0,0,0,
            ScoreUpdateUseCase.Players.A
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(2, actual.playerA.score.gamePoint)
    }

    @Test
    fun `should return game point 3 for player a`() {
        val mockObject = getMatchMockObject(
            2,0,0,
            0,0,0,
            ScoreUpdateUseCase.Players.A
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(3, actual.playerA.score.gamePoint)
    }

    @Test
    fun `should return game point 0 for player a`() {
        val mockObject = getMatchMockObject(
            3,0,0,
            0,0,0,
            ScoreUpdateUseCase.Players.A
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(0, actual.playerA.score.gamePoint)
    }

    @Test
    fun `should return deuce true when player A point increased`() {
        val mockObject = getMatchMockObject(
            2,0,0,
            3,0,0,
            ScoreUpdateUseCase.Players.A
        )
        val actual = useCase.execute(mockObject)
        Assert.assertTrue(actual.deuce)
    }

    @Test
    fun `should return game point 1 for player b`() {
        val mockObject = getMatchMockObject(pointTo = ScoreUpdateUseCase.Players.B)
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(1, actual.playerB.score.gamePoint)
    }

    @Test
    fun `should return game point 2 for player b`() {
        val mockObject = getMatchMockObject(
            0,0,0,
            1,0,0,
            ScoreUpdateUseCase.Players.B
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(2, actual.playerB.score.gamePoint)
    }

    @Test
    fun `should return game point 3 for player b`() {
        val mockObject = getMatchMockObject(
            0,0,0,
            2,0,0,
            ScoreUpdateUseCase.Players.B
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(3, actual.playerB.score.gamePoint)
    }

    @Test
    fun `should return game point 0 for player b`() {
        val mockObject = getMatchMockObject(
            0,0,0,
            3,0,0,
            ScoreUpdateUseCase.Players.B
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(0, actual.playerB.score.gamePoint)
    }

    @Test
    fun `should return deuce true when player B point increased`() {
        val mockObject = getMatchMockObject(
            3,0,0,
            2,0,0,
            ScoreUpdateUseCase.Players.B
        )
        val actual = useCase.execute(mockObject)
        Assert.assertTrue(actual.deuce)
    }

    @Test
    fun `should return 4 when deuce true and player A point increased`() {
        val mockObject = getMatchMockObject(
            3,0,0,
            3,0,0,
            ScoreUpdateUseCase.Players.A, true
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(4, actual.playerA.score.gamePoint)
    }

    @Test
    fun `should return 4 when deuce true and player B point increased`() {
        val mockObject = getMatchMockObject(
            3,0,0,
            3,0,0,
            ScoreUpdateUseCase.Players.B, true
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(4, actual.playerB.score.gamePoint)
    }

    @Test
    fun `should return 0 when deuce is true and difference of player A - B point is 2`() {
        val mockObject = getMatchMockObject(
            4,0,0,
            3,0,0,
            ScoreUpdateUseCase.Players.A, true
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(0, actual.playerA.score.gamePoint)
        Assert.assertEquals(0, actual.playerB.score.gamePoint)
    }

    @Test
    fun `should return 0 when deuce is true and difference of player B - A point is 2`() {
        val mockObject = getMatchMockObject(
            3,0,0,
            4,0,0,
            ScoreUpdateUseCase.Players.B, true
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(0, actual.playerB.score.gamePoint)
        Assert.assertEquals(0, actual.playerA.score.gamePoint)
    }

    @Test
    fun `should return 3 when you increase player A point and deuce again`() {
        val mockObject = getMatchMockObject(
            3,0,0,
            4,0,0,
            ScoreUpdateUseCase.Players.A, true
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(3, actual.playerB.score.gamePoint)
        Assert.assertEquals(3, actual.playerA.score.gamePoint)
    }

    @Test
    fun `should return 3 when you increase player B point and deuce again`() {
        val mockObject = getMatchMockObject(
            4,0,0,
            3,0,0,
            ScoreUpdateUseCase.Players.B, true
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(3, actual.playerB.score.gamePoint)
        Assert.assertEquals(3, actual.playerA.score.gamePoint)
    }


    @Test
    fun `should return set point 1 when player A is game`() {
        val mockObject = getMatchMockObject(
            3,0,0,
            0,0,0,
            ScoreUpdateUseCase.Players.A
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(1, actual.playerA.score.setPoint)
        Assert.assertEquals(0, actual.playerA.score.gamePoint)
        Assert.assertEquals(0, actual.playerB.score.gamePoint)
    }

    @Test
    fun `should return set point 2 when player A is game again`() {
        val mockObject = getMatchMockObject(
            3,1,0,
            0,0,0,
            ScoreUpdateUseCase.Players.A
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(2, actual.playerA.score.setPoint)
        Assert.assertEquals(0, actual.playerA.score.gamePoint)
        Assert.assertEquals(0, actual.playerB.score.gamePoint)
    }

    @Test
    fun `should return set point 0 when player A set point reaches to 6`() {
        val mockObject = getMatchMockObject(
            3,5,0,
            0,0,0,
            ScoreUpdateUseCase.Players.A
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(0, actual.playerA.score.setPoint)
        Assert.assertEquals(0, actual.playerA.score.gamePoint)
        Assert.assertEquals(0, actual.playerB.score.gamePoint)
    }

    @Test
    fun `should return match point 1 when player A set point reaches to 6`() {
        val mockObject = getMatchMockObject(
            3,5,0,
            0,1,0,
            ScoreUpdateUseCase.Players.A
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(1, actual.playerA.score.matchPoint)
        Assert.assertEquals(0, actual.playerA.score.setPoint)
        Assert.assertEquals(0, actual.playerA.score.gamePoint)
        Assert.assertEquals(0, actual.playerB.score.gamePoint)
        Assert.assertEquals(0, actual.playerB.score.setPoint)
    }

    @Test
    fun `should not update the score after match win`() {
        val mockObject = getMatchMockObject(
            0,0,2,
            0,0,0,
            ScoreUpdateUseCase.Players.A
        )
        val actual = useCase.execute(mockObject)
        Assert.assertEquals(2, actual.playerA.score.matchPoint)
        Assert.assertEquals(0, actual.playerA.score.setPoint)
        Assert.assertEquals(0, actual.playerA.score.gamePoint)
        Assert.assertEquals(0, actual.playerB.score.gamePoint)
        Assert.assertEquals(0, actual.playerB.score.setPoint)
    }
}