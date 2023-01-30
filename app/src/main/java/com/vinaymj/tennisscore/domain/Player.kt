package com.vinaymj.tennisscore.domain

import com.vinaymj.tennisscore.domain.model.Score

class Player {

    val score: Score = Score()

    fun increaseGamePoint() {
        score.updateGamePoint()
    }

    fun increaseSetPoint() {
        score.updateSetPoint()
    }

    fun increaseMatchPoint() {
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
}