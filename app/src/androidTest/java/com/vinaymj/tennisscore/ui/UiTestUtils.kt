package com.vinaymj.tennisscore.ui

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.performClick


fun performOneFullSet(onNodeWithTag: SemanticsNodeInteraction) {
    for(i in 1..4) {
        onNodeWithTag.performClick()
    }
}

fun performOneFullMatch(onNodeWithTag: SemanticsNodeInteraction) {
    for(i in 1..6) {
        performOneFullSet(onNodeWithTag)
    }
}

fun performDeuce(playerANode: SemanticsNodeInteraction,
                 playerBNode: SemanticsNodeInteraction) {
    for(i in 1..3) {
        playerANode.performClick()
        playerBNode.performClick()
    }
}