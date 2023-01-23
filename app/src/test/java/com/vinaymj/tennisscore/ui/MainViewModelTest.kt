package com.vinaymj.tennisscore.ui

import com.vinaymj.tennisscore.domain.ScoreUpdateUseCase
import com.vinaymj.tennisscore.domain.model.MatchScore
import com.vinaymj.tennisscore.domain.model.Player
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class MainViewModelTest {

    @MockK
    private val useCase = mockk<ScoreUpdateUseCase>()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(useCase)
    }

    @Test
    fun `update player A game point by 1`() {
        val matchMockObject = MatchScore(
            Player("1", 0, 0),
            Player("0", 0, 0),
            ScoreUpdateUseCase.PointTo.A,
            false
        )
        every { useCase.execute(any()) } returns matchMockObject
        viewModel.updateScore(ScoreUpdateUseCase.PointTo.A)
        val result = viewModel.scoreBoardState.value
        Assert.assertEquals("1",result.match.playerA.gamePoint)
    }

    @Test
    fun `reset score board`() {
        val matchMockObject = MatchScore(
            Player("0", 0, 0),
            Player("0", 0, 0),
            ScoreUpdateUseCase.PointTo.NON,
            false
        )
        every { useCase.resetScoreBoard() } returns matchMockObject
        viewModel.resetMatchScore()
        val result = viewModel.scoreBoardState.value
        Assert.assertEquals("0",result.match.playerA.gamePoint)
    }

}