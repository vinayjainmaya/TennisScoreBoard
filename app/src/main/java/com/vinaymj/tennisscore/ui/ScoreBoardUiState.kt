package com.vinaymj.tennisscore.ui

import com.vinaymj.tennisscore.domain.ScoreUpdateUseCase.PointTo
import com.vinaymj.tennisscore.domain.model.MatchScore
import com.vinaymj.tennisscore.domain.model.PlayerScore

data class ScoreBoardUiState(
    val match: MatchScore = MatchScore(
        playerAScore = PlayerScore(
            gamePoint = "0",
            setPoint = 0,
            matchPoint = 0
        ),
        playerBScore = PlayerScore(
            gamePoint = "0",
            setPoint = 0,
            matchPoint = 0
        ),
        pointTo = PointTo.NON,
        someBodyWin = false
    )
)
