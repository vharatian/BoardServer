package com.vahid.board.score.domain

import java.util.*

abstract class PlayerRepository {

    fun loadWithLock(playerId: UUID): PlayerAggregate? {
        val player = loadPlayer(playerId)

        return if (player != null)
            PlayerAggregate(player)
        else
            null
    }

    fun save(playerAggregate: PlayerAggregate){
        savePlayer(playerAggregate.player)
    }

    protected abstract fun savePlayer(player: Player)
    protected abstract fun loadPlayer(playerId: UUID): Player?
}
