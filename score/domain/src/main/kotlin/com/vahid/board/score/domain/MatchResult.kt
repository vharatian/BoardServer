package com.vahid.board.score.domain

import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotEmpty

class MatchResult(
        @NotEmpty
        val points: List<PlayerPoint>
) {
    init {
        ValidationUtil.validateOrThrow(this)
    }

    @AssertTrue
    fun isWinnerWithHighestPoint(): Boolean{
        return points.first().point == points.maxBy { it.point }!!.point
    }
}