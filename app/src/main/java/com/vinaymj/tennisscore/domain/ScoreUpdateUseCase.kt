package com.vinaymj.tennisscore.domain

import com.vinaymj.tennisscore.domain.model.Match
import javax.inject.Inject


class ScoreUpdateUseCase @Inject constructor() {


    fun execute(match: Match) = match.apply {
        matchScore()
    }


    enum class PointTo {
        A,
        B,
        NON
    }
}