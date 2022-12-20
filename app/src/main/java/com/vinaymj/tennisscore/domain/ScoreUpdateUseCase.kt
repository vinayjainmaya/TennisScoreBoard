package com.vinaymj.tennisscore.domain

import com.vinaymj.tennisscore.common.isDeuce
import com.vinaymj.tennisscore.common.isGame
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
                when {
                    isDeuce(
                        match.playerA.score.gamePoint,
                        match.playerB.score.gamePoint,
                        match.deuce
                    ) -> {
                        match.deuce = true
                        match.playerA.score.gamePoint = 3
                        match.playerB.score.gamePoint = 3
                    }
                    isGame(
                        match.playerA.score.gamePoint,
                        match.playerB.score.gamePoint,
                        match.deuce
                    ) -> {
                        match.deuce = false
                        match.playerA.score.gamePoint = 0
                        match.playerB.score.gamePoint = 0
                        // updateSetPoint()
                    }
                }
                match
            }
            Players.B -> {
                match.playerB.score.gamePoint++
                when {
                    isDeuce(
                        match.playerB.score.gamePoint,
                        match.playerA.score.gamePoint,
                        match.deuce
                    ) -> {
                        match.deuce = true
                        match.playerB.score.gamePoint = 3
                        match.playerA.score.gamePoint = 3
                    }
                    isGame(
                        match.playerB.score.gamePoint,
                        match.playerA.score.gamePoint,
                        match.deuce
                    ) -> {
                        match.deuce = false
                        match.playerA.score.gamePoint = 0
                        match.playerB.score.gamePoint = 0
                        // updateSetPoint()
                    }
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