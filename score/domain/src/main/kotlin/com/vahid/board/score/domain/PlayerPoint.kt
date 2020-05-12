package com.vahid.board.score.domain

import java.util.*

class PlayerPoint(
        val playerId: UUID,
        val point: GamePoint
) {
    init {
        ValidationUtil.validateOrThrow(this)
    }
}
