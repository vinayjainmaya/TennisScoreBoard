package com.vinaymj.tennisscore.domain.model

data class Player(
    val score: Score
){
    fun updateGamePoint() {
        score.gamePoint++
    }

    fun updateSetPoint() {
        score.apply {
            setPoint++
            gamePoint = 0
        }
    }

    fun updateMatchPoint() {
        score.apply {
            matchPoint++
            setPoint = 0
            gamePoint = 0
        }
    }

    fun resetGamePoint() {
        score.gamePoint = 0
    }

    fun resetSetPoint() {
        score.setPoint = 0
    }

    fun setDeucePoint() {
        score.gamePoint = 3
    }

    fun wins() = (score.matchPoint == 2)
}
