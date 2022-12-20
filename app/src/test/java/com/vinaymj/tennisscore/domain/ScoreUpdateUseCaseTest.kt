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

}