package com.vinaymj.tennisscore.domain

import com.vinaymj.tennisscore.common.isDeuce
import com.vinaymj.tennisscore.common.isGame
import com.vinaymj.tennisscore.domain.model.Match
import javax.inject.Inject


class ScoreUpdateUseCase @Inject constructor() {


    fun execute(match: Match): Match {
        if(match.playerA.score.matchPoint == 2
            || match.playerB.score.matchPoint == 2) {
            return match
        }
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
                        updateSetPoint(match)
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
                        updateSetPoint(match)
                    }
                }
                match
            }
            else -> {
                match
            }
        }
    }

    private fun updateSetPoint(match: Match) {
        when(match.pointTo) {
            Players.A -> {
                match.playerA.score.setPoint++
                when {
                    (match.playerA.score.setPoint == 6) -> {
                        match.tieBreak = false
                        match.playerA.score.apply {
                            matchPoint++
                            setPoint = 0
                            gamePoint = 0
                        }
                        match.playerB.score.apply {
                            setPoint = 0
                            gamePoint = 0
                        }
                    }
                }
            }
            Players.B -> {
                match.playerB.score.setPoint++
                when {
                    (match.playerB.score.setPoint == 6) -> {
                        match.tieBreak = false
                        match.playerB.score.apply {
                            matchPoint++
                            setPoint = 0
                            gamePoint = 0
                        }
                        match.playerA.score.apply {
                            setPoint = 0
                            gamePoint = 0
                        }
                    }
                }
            }
        }
    }


    enum class Players {
        A,
        B,
        NON
    }
}