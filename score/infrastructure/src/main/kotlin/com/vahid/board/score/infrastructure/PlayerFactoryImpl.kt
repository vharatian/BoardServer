package com.vahid.board.score.infrastructure

import com.vahid.board.score.domain.Player
import com.vahid.board.score.domain.PlayerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlayerFactoryImpl: PlayerFactory() {
    override fun createEmptyAccount(playerId: UUID): Player {
        return JpaPlayer(playerId)
    }
}