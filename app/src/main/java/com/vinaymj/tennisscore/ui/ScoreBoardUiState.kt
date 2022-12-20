package com.vinaymj.tennisscore.ui

import com.vinaymj.tennisscore.domain.ScoreUpdateUseCase.Players
import com.vinaymj.tennisscore.domain.model.Match
import com.vinaymj.tennisscore.domain.model.Player
import com.vinaymj.tennisscore.domain.model.Score

data class ScoreBoardUiState(
    val match: Match = Match(
        playerA = Player(
            score = Score(
                gamePoint = 0,
                set = 0,
                match = 0
            )
        ),
        playerB = Player(
            score = Score(
                gamePoint = 0,
                set = 0,
                match = 0
            )
        ),
        deuce = false,
        tieBreak = false,
        pointTo = Players.NON
    )
)
