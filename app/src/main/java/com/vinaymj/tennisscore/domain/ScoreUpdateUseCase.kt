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
                if(match.playerA.score.gamePoint == 4) {
                    match.deuce = false
                    match.playerA.score.gamePoint = 0
                    match.playerB.score.gamePoint = 0
                    // updateSetPoint()
                }
                match
            }
            Players.B -> {
                match.playerB.score.gamePoint++
                if(match.playerB.score.gamePoint == 4) {
                    match.deuce = false
                    match.playerA.score.gamePoint = 0
                    match.playerB.score.gamePoint = 0
                    // updateSetPoint()
                }
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