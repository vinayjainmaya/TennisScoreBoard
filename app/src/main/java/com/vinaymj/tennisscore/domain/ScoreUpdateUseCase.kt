package com.vinaymj.tennisscore.domain

import com.vinaymj.tennisscore.domain.model.MatchScore
import javax.inject.Inject


class ScoreUpdateUseCase @Inject constructor() {


    fun execute(pointTo: PointTo): MatchScore {
        Match.updateScore(pointTo)

        return Match.toMatchScore()
    }

    fun resetScoreBoard(): MatchScore {
        Match.resetMatchScore()
        return Match.toMatchScore()
    }

    enum class PointTo {
        A,
        B,
        NON
    }
}