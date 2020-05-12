package com.vahid.board.match.domain

import javax.validation.constraints.PositiveOrZero

interface GamePoint: Comparable<GamePoint> {

    companion object{
        fun newInstance(value: Int): GamePoint {
            var factory = DiUtils.getBean(GamePointFactory::class.java)
            return factory.newInstance(value)
        }
    }

    val value: Int
        @PositiveOrZero
        get

    override operator fun compareTo(other: GamePoint): Int {
        return value.compareTo(other.value)
    }
}