package com.vinaymj.tennisscore.ui

import com.vinaymj.tennisscore.domain.ScoreUpdateUseCase
import com.vinaymj.tennisscore.getMatchMockObject
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
        val matchMockObject = getMatchMockObject(
            1,0,0,
            0,0,0,ScoreUpdateUseCase.Players.A
        )
        every { useCase.execute(any()) } returns matchMockObject
        viewModel.updateScore(ScoreUpdateUseCase.Players.A)
        val result = viewModel.scoreBoardState.value
        Assert.assertEquals(result.match.playerA.score.gamePoint, 1)
    }

    @Test
    fun `reset score board`() {

        viewModel.resetMatchScore()
        val result = viewModel.scoreBoardState.value
        Assert.assertEquals(result.match.playerA.score.gamePoint, 0)
    }

}