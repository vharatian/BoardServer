package com.vahid.board.score.domain

import java.util.*

interface Player {
    val id: UUID
    var score: Score
    var balance: Money
}