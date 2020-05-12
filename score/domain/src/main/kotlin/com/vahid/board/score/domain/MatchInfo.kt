package com.vahid.board.score.domain

import javax.validation.constraints.*

class MatchInfo(
        @Positive(message = "coins should be more than 0")
        val coins: Int,
        val winnerIndex: Int,
        @NotEmpty
        val points: List<PlayerPoint>
) {
    init {
        ValidationUtil.validateOrThrow(this)
    }

    @AssertTrue(message = "winnerIndex should be between 0 and points size")
    fun isWinnerIndexWithinBounds(): Boolean {
        return winnerIndex > -1 && winnerIndex <= points.size
    }

    @AssertFalse(message= "player can not participate more than once in a single match")
    fun isAnyDuplicatedPlayerId(): Boolean {
        return points.map { it.playerId }
                .toSet().size != points.size
    }

    @AssertTrue(message= "winner should have highest point")
    fun isWinnerWithHighestPoint(): Boolean{
        return points[winnerIndex].point >= points.maxBy { it.point }!!.point
    }
}
