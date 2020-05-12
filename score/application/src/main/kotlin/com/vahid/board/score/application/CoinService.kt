package com.vahid.board.score.application

import com.vahid.board.score.domain.Money
import com.vahid.board.score.domain.PlayerFactory
import com.vahid.board.score.domain.PlayerRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class CoinService(
        val playerRepository: PlayerRepository,
        val playerFactory: PlayerFactory
) {

    private fun loadPlayer(playerId: UUID) =
            playerRepository.loadWithLock(playerId) ?: playerFactory.createAccount(playerId)

    fun withdraw(playerId: UUID, dto: MoneyDto) {
        val player = loadPlayer(playerId)
        player.withdraw(dto.toMoney())
        playerRepository.save(player)
    }

    fun deposit(playerId: UUID, dto: MoneyDto) {
        val player = loadPlayer(playerId)
        player.deposit(dto.toMoney())
        playerRepository.save(player)
    }
}