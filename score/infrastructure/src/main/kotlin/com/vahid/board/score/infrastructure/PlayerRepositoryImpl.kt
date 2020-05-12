package com.vahid.board.score.infrastructure

import com.vahid.board.score.domain.Player
import com.vahid.board.score.domain.PlayerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlayerRepositoryImpl(private val jpaPlayerRepository: JpaPlayerRepository) : PlayerRepository() {

    override fun savePlayer(player: Player) {
        jpaPlayerRepository.save(player as JpaPlayer)
    }

    override fun loadPlayer(playerId: UUID): Player? {
        return jpaPlayerRepository.findPlayerById(playerId)
    }
}