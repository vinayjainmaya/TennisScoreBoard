package com.vinaymj.tennisscore.domain.model

import com.vinaymj.tennisscore.common.Constants
import com.vinaymj.tennisscore.domain.ScoreUpdateUseCase.PointTo

data class Match(
    val playerA: Player,
    val playerB: Player,
    var pointTo: PointTo,
    var deuce: Boolean,
    var tieBreak: Boolean,
) {

    fun matchScore() {
        if (someBodyWins()) {
            return
        }
        // Swap the values to handle same logic for both players
        val tempPlayerA = if (pointTo == PointTo.A) playerA else playerB
        val tempPlayerB = if (pointTo == PointTo.B) playerA else playerB

        tempPlayerA.updateGamePoint()
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

    private fun updateSetPoint(tempPlayerA: Player, tempPlayerB: Player) {
        tempPlayerA.updateSetPoint()
        if (tempPlayerA.score.setPoint == 6) {
            tieBreak = false
            tempPlayerA.updateMatchPoint()
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

    private fun gamePoint(tempPlayerA: Player, tempPlayerB: Player): Boolean {

        return if (deuce) {
            (tempPlayerA.score.gamePoint - tempPlayerB.score.gamePoint) == 2
        } else {
            tempPlayerA.score.gamePoint == 4
        }
    }

    fun clone(): Match {
        return this.copy(playerA = playerA.copy(score = playerA.score.copy()),
            playerB = playerB.copy(score = playerB.score.copy()),
            pointTo = pointTo,
            deuce = deuce,
            tieBreak = tieBreak)
    }

    fun displayGamePoint(point: Int): String {
        return if (deuce && point == 4) {
            "AD"
        } else {
            Constants.DISPLAY_GP[point].toString()
        }
    }

    fun someBodyWins() = (playerA.score.matchPoint == 2 || playerB.score.matchPoint == 2)

}
