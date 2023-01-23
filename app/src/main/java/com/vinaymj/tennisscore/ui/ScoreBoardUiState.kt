package com.vinaymj.tennisscore.ui

import com.vinaymj.tennisscore.domain.ScoreUpdateUseCase.PointTo
import com.vinaymj.tennisscore.domain.model.MatchScore
import com.vinaymj.tennisscore.domain.model.Player

data class ScoreBoardUiState(
    val match: MatchScore = MatchScore(
        playerA = Player(
            gamePoint = "0",
            setPoint = 0,
            matchPoint = 0
        ),
        playerB = Player(
            gamePoint = "0",
            setPoint = 0,
            matchPoint = 0
        ),
        pointTo = PointTo.NON,
        someBodyWin = false
    )
)
