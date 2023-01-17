package com.vinaymj.tennisscore.domain.model

data class Score(
    var gamePoint: Int = 0,
    var setPoint: Int = 0,
    var matchPoint: Int = 0
) {
    fun updateGamePoint() {
        gamePoint++
    }

    fun updateSetPoint() {
        setPoint++
        gamePoint = 0
    }

    fun updateMatchPoint() {
        matchPoint++
        setPoint = 0
        gamePoint = 0
    }

    fun resetGamePoint() {
        gamePoint = 0
    }

    fun resetSetPoint() {
        setPoint = 0
    }

    fun setDeucePoint() {
        gamePoint = 3
    }
}
