package com.vinaymj.tennisscore.domain.model

data class Player(
    val score: Score
){
    fun updateGamePoint() {
        score.updateGamePoint()
    }

    fun updateSetPoint() {
        score.updateSetPoint()
    }

    fun updateMatchPoint() {
        score.updateMatchPoint()
    }

    fun resetGamePoint() {
        score.resetGamePoint()
    }

    fun resetSetPoint() {
        score.resetSetPoint()
    }

    fun setDeucePoint() {
        score.setDeucePoint()
    }

    fun wins() = (score.matchPoint == 2)
}
