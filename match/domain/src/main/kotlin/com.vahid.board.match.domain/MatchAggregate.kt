package com.vahid.board.match.domain

import java.util.*

class MatchAggregate internal constructor(
        internal val match: Match,
        private val moneyServiceAdapter: MoneyServiceAdapter,
        private val scoreServiceAdapter: ScoreServiceAdapter
) {

    fun inviteGust(gustId: UUID) {
        match.inviteGust(gustId)
    }

    fun chargeCreationCost() {
        match.chargeCreationCost(moneyServiceAdapter)
    }

    fun joinPlayer(playerId: UUID) {
        match.joinPlayer(playerId, moneyServiceAdapter)
    }

    fun reportResult(result: MatchResultDto) {
        match.reportResult(result)
    }

    fun confirmResult(playerId: UUID) {
        match.confirmResult(playerId, moneyServiceAdapter, scoreServiceAdapter)
    }
}
