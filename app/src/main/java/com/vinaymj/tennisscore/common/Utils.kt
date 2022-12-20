package com.vinaymj.tennisscore.common

import com.vinaymj.tennisscore.domain.model.Match


fun isDeuce(playerAGP: Int, playerBGP: Int, deuce: Boolean): Boolean {
    return if(deuce){
        (playerAGP ==  playerBGP)
    } else {
        (playerAGP == 3 && playerBGP == 3)
    }
}

fun isGame(playerAGP: Int, playerBGP: Int, deuce: Boolean): Boolean {
    return if(deuce) {
        (playerAGP - playerBGP) == 2
    } else {
        playerAGP == 4
    }
}

fun getDisplayGamePoint(key: Int, deuce: Boolean): String {
    return if(deuce && key == 4) {
        "AD"
    } else {
        Constants.DISPLAY_GP[key].toString()
    }
}

fun cloneMatchObject(match: Match): Match {
    return match.copy(
        playerA = match.playerA.copy(score = match.playerA.score.copy()),
        playerB = match.playerB.copy(score = match.playerB.score.copy()),
        pointTo = match.pointTo,
        deuce = match.deuce,
        tieBreak = match.tieBreak
    )
}
