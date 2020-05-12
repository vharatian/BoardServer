package com.vahid.board.match.domain

import java.util.*
import javax.validation.Valid
import javax.validation.constraints.AssertFalse
import javax.validation.constraints.AssertTrue

interface MatchResult {
    val reporterId: UUID
    val points: List<@Valid PlayerPoint>

    @AssertFalse
    fun isAnyDuplicatedPlayer(): Boolean {
        val uniquePlayersCount = points.map { point -> point.playerId }.toSet().size
        return points.size != uniquePlayersCount
    }

    @AssertTrue
    fun isFirstIndexTheWinner(): Boolean{
        return points.first().point == points.maxBy { it.point }!!.point
    }

    @AssertTrue
    fun isReporterHasAPoint(): Boolean{
        return points.any { it.playerId == reporterId }
    }

    fun getWinnerId(): UUID {
        return points.first().playerId
    }

    fun confirm(playerId: UUID) {
        val point = points.singleOrNull { it.playerId == playerId }
                ?: throw BusinessException("player does not have a reported point")

        if (point.confirmed) {
            throw BusinessException("You have confirmed before")
        }

        point.confirmed = true
    }

    fun isFinalized(): Boolean {
        return points.all { it.confirmed }
    }

}