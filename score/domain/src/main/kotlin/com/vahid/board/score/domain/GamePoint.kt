package com.vahid.board.score.domain

import javax.validation.constraints.PositiveOrZero

data class GamePoint(
        @PositiveOrZero
        val value: Int
) : Comparable<GamePoint> {
    init {
        ValidationUtil.validateOrThrow(this)
    }

    override operator fun compareTo(other: GamePoint): Int{
        return value.compareTo(other.value)
    }

    operator fun plus(other: GamePoint): GamePoint{
        return GamePoint(value + other.value)
    }

    operator fun div(other: GamePoint): Double {
        return value.toDouble() / other.value.toDouble()
    }
}
