package com.vinaymj.tennisscore

import com.vinaymj.tennisscore.domain.ScoreUpdateUseCase.Players
import com.vinaymj.tennisscore.domain.model.Match
import com.vinaymj.tennisscore.domain.model.Player
import com.vinaymj.tennisscore.domain.model.Score

fun getMatchMockObject(
    aGamePoint: Int = 0,
    aSetPoint: Int = 0,
    aMatchPoint: Int = 0,
    bGamePoint: Int = 0,
    bSetPoint: Int = 0,
    bMatchPoint: Int = 0,
    pointTo: Players = Players.A,
    deuce: Boolean = false,
    tiebreak: Boolean = false
): Match {
    return Match(
        playerA = Player(score = Score(
            aGamePoint,aSetPoint,aMatchPoint
        )),
        playerB = Player(score = Score(
            bGamePoint,bSetPoint,bMatchPoint
        )),
        pointTo = pointTo,
        deuce = deuce,
        tieBreak = tiebreak
    )
}
