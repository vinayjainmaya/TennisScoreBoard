package com.vinaymj.tennisscore.domain.model

import com.vinaymj.tennisscore.domain.ScoreUpdateUseCase.PointTo

data class MatchScore(
    val playerA: Player,
    val playerB: Player,
    val pointTo: PointTo,
    val someBodyWin: Boolean
)
