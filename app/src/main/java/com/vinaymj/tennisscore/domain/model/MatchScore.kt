package com.vinaymj.tennisscore.domain.model

import com.vinaymj.tennisscore.domain.ScoreUpdateUseCase.PointTo

data class MatchScore(
    val playerAScore: PlayerScore,
    val playerBScore: PlayerScore,
    val pointTo: PointTo,
    val someBodyWin: Boolean
)
