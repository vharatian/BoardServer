package com.vahid.board.score.domain

abstract class ScoreFactory {

    fun newValidScore(value: Double): Score{
        val score = newScore(value)

        ValidationUtil.validateOrThrow(score)

        return score
    }

    protected abstract fun newScore(value: Double): Score
}