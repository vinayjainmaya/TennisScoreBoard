package com.vinaymj.tennisscore.domain

import com.vinaymj.tennisscore.domain.model.Score


interface ScoreListener {
    val score: Score
    fun increaseGamePoint()
    fun increaseSetPoint()
    fun increaseMatchPoint()
    fun resetGamePoint()
    fun resetSetPoint()
    fun setDeucePoint()
}