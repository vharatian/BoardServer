package com.vahid.board.score.domain

import java.util.*

abstract class PlayerFactory {
    fun createAccount(playerId: UUID): PlayerAggregate{
        val player = createEmptyAccount(playerId)

        player.balance = Money.newInstance(1000)
        player.score = Score.newInstance(0.0)

        return PlayerAggregate(player)
    }

    protected abstract fun createEmptyAccount(playerId: UUID): Player
}