package com.vahid.board.match.domain

import java.util.*
import javax.validation.constraints.Positive

class PlayerPointDto(
        val playerId: UUID,
        val point: Int)
