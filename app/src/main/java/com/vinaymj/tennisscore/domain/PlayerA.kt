package com.vinaymj.tennisscore.domain

import com.vinaymj.tennisscore.domain.model.Score

class PlayerA: ScoreListener {

    override val score: Score = Score()

    override fun increaseGamePoint() {
        score.updateGamePoint()
    }

    override fun increaseSetPoint() {
        score.updateSetPoint()
    }

    override fun increaseMatchPoint() {
        score.updateMatchPoint()
    }

    override fun resetGamePoint() {
        score.resetGamePoint()
    }

    override fun resetSetPoint() {
        score.resetSetPoint()
    }

    override fun setDeucePoint() {
        score.setDeucePoint()
    }
}