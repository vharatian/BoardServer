package com.vahid.board.match.domain

import java.util.*

interface Match {

    val id: UUID
    val creatorId: UUID
    val entranceCost: Money
    var joinedPlayersId: Set<UUID>
    var invitedPlayersId: Set<UUID>
    var matchResult: MatchResult?

    fun createResult(dto: MatchResultDto): MatchResult

    fun getTotalCoins(): Money {
        return entranceCost * joinedPlayersId.size
    }

    fun inviteGust(gustId: UUID) {
        if (joinedPlayersId.contains(gustId)) {
            throw BusinessException("player can not participate in match more than once")
        }

        invitedPlayersId = invitedPlayersId + gustId
    }

    fun chargeCreationCost(moneyServiceAdapter: MoneyServiceAdapter) {
        if (joinedPlayersId.isNotEmpty()) {
            throw BusinessException("Cost has been charged before")
        }

        moneyServiceAdapter.withdraw(creatorId, entranceCost)
        joinedPlayersId = setOf(creatorId)
    }

    fun joinPlayer(playerId: UUID, moneyServiceAdapter: MoneyServiceAdapter) {
        if (matchResult != null) {
            throw BusinessException("Can not join a match with result")
        }

        if (invitedPlayersId.contains(playerId)) {
            throw BusinessException("Only invited players can join match")
        }

        moneyServiceAdapter.withdraw(playerId, entranceCost)
        invitedPlayersId = invitedPlayersId - playerId
        joinedPlayersId = joinedPlayersId + playerId
    }

    fun reportResult(dto: MatchResultDto) {
        if (matchResult != null) {
            throw BusinessException("Match already has a result")
        }

        if (dto.reporterId != creatorId) {
            throw BusinessException("Only creator can report a result")
        }

        if (joinedPlayersId != dto.points.map { it.playerId }) {
            throw BusinessException("Each player should have a point on reported result")
        }

        val result = createResult(dto)
        ValidationUtil.validateOrThrow(result)

        matchResult = result
    }

    fun confirmResult(playerId: UUID, moneyServiceAdapter: MoneyServiceAdapter, scoreServiceAdapter: ScoreServiceAdapter) {
        matchResult?.confirm(playerId) ?: throw BusinessException("No result reported")

        if (matchResult!!.isFinalized()) {
            moneyServiceAdapter.deposit(matchResult!!.getWinnerId(), getTotalCoins())
            scoreServiceAdapter.applyResultScore(matchResult!!)
        }
    }
}
