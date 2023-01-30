package com.vinaymj.tennisscore.domain

import com.vinaymj.tennisscore.domain.model.MatchScore
import javax.inject.Inject


class ScoreUpdateUseCase @Inject constructor(private val match: Match) {


    fun execute(pointTo: PointTo): MatchScore {
        match.updateScore(pointTo)

        return match.toMatchScore()
    }

    fun resetScoreBoard(): MatchScore {
        match.resetMatchScore()
        return match.toMatchScore()
    }

    enum class PointTo {
        A,
        B,
        NON
    }
}