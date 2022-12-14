package com.vinaymj.tennisscore.domain.model

import com.vinaymj.tennisscore.domain.ScoreUpdateUseCase.Players

data class Match(
    val playerA : Player,
    val playerB : Player,
    var pointTo : Players,
    var deuce: Boolean,
    var tieBreak: Boolean
)
