package com.vahid.board.match.domain

import java.util.*
import javax.validation.constraints.Positive

interface PlayerPoint {
    var confirmed: Boolean
    val playerId: UUID
    val point: GamePoint
}
