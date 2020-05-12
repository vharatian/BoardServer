package com.vahid.board.score.domain

import javax.validation.constraints.PositiveOrZero

interface Score {

    companion object {
        fun newInstance(value: Double): Score {
            val factory = DiUtils.getBean(ScoreFactory::class.java)
            return factory.newValidScore(value)
        }
    }

    val value: Double
        @PositiveOrZero
        get

    operator fun plus(other: Score): Score {
        return newInstance(value + other.value)
    }
}