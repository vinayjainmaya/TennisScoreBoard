package com.vinaymj.tennisscore.domain.model

data class PlayerScore(
   var gamePoint: String = "",
   var setPoint: Int = 0,
   var matchPoint: Int = 0
) {
    fun wins() = matchPoint == 2
}
