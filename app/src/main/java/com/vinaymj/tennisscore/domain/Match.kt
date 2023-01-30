package com.vinaymj.tennisscore.domain

import com.vinaymj.tennisscore.common.Constants
import com.vinaymj.tennisscore.domain.model.MatchScore
import com.vinaymj.tennisscore.domain.model.Player
import javax.inject.Inject

class Match @Inject constructor() {

    var playerA: PlayerA = PlayerA()
        private set
    var playerB: PlayerB = PlayerB()
        private set
    var pointTo: ScoreUpdateUseCase.PointTo = ScoreUpdateUseCase.PointTo.NON
        private set
    var deuce: Boolean = false
        private set


    fun updateScore(point_to: ScoreUpdateUseCase.PointTo) {
        pointTo = point_to
        if (someBodyWins()) {
            return
        }
        // Swap the values to handle same logic for both players
        val tempPlayerA = if (pointTo == ScoreUpdateUseCase.PointTo.A) playerA else playerB
        val tempPlayerB = if (pointTo == ScoreUpdateUseCase.PointTo.B) playerA else playerB

        tempPlayerA.increaseGamePoint()
        when {
            deuce() -> {
                deuce = true
                tempPlayerA.setDeucePoint()
                tempPlayerB.setDeucePoint()
            }
            gamePoint(tempPlayerA, tempPlayerB) -> {
                deuce = false
                tempPlayerA.resetGamePoint()
                tempPlayerB.resetGamePoint()
                updateSetPoint(tempPlayerA, tempPlayerB)
            }
        }
    }

    private fun updateSetPoint(tempPlayerA: ScoreListener, tempPlayerB: ScoreListener) {
        tempPlayerA.increaseSetPoint()
        if (tempPlayerA.score.setPoint == 6) {
            tempPlayerA.increaseMatchPoint()
            tempPlayerB.resetGamePoint()
            tempPlayerB.resetSetPoint()
        }

    }

    private fun deuce(): Boolean {
        return if (deuce) {
            (playerA.score.gamePoint == playerB.score.gamePoint)
        } else {
            (playerA.score.gamePoint == 3 && playerB.score.gamePoint == 3)
        }
    }

    private fun gamePoint(tempPlayerA: ScoreListener, tempPlayerB: ScoreListener): Boolean {

        return if (deuce) {
            (tempPlayerA.score.gamePoint - tempPlayerB.score.gamePoint) == 2
        } else {
            tempPlayerA.score.gamePoint == 4
        }
    }

    private fun displayGamePoint(point: Int): String {
        return if (deuce && point == 4) {
            "AD"
        } else {
            Constants.DISPLAY_GP[point].toString()
        }
    }

    private fun someBodyWins() = (playerA.score.matchPoint == 2 || playerB.score.matchPoint == 2)

    fun resetMatchScore() {
        playerA = PlayerA()
        playerB = PlayerB()
        pointTo = ScoreUpdateUseCase.PointTo.NON
        deuce = false
    }

    fun toMatchScore() = MatchScore(
        playerA = Player(
            gamePoint = displayGamePoint(playerA.score.gamePoint),
            setPoint = playerA.score.setPoint,
            matchPoint = playerA.score.matchPoint
        ),
        playerB = Player(
            gamePoint = displayGamePoint(playerB.score.gamePoint),
            setPoint = playerB.score.setPoint,
            matchPoint = playerB.score.matchPoint
        ),
        pointTo = pointTo,
        someBodyWin = someBodyWins()
    )

}