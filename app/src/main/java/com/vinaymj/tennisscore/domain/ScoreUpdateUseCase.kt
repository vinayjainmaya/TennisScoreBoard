package com.vinaymj.tennisscore.domain

import com.vinaymj.tennisscore.domain.model.Match
import javax.inject.Inject


class ScoreUpdateUseCase @Inject constructor() {


    fun execute(match: Match): Match {
        return updateGamePoint(match)
    }

    private fun updateGamePoint(match: Match): Match {
        return when(match.pointTo) {
            Players.A -> {
                match.playerA.score.gamePoint++
                match
            }
            Players.B -> {
                match.playerB.score.gamePoint++
                match
            }
            else -> {
                match
            }
        }
    }

    enum class Players {
        A,
        B,
        NON
    }
}