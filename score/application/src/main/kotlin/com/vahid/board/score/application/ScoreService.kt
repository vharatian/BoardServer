package com.vahid.board.score.application

import com.vahid.board.score.domain.*
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class ScoreService(private val playerRepository: PlayerRepository, private val playerFactory: PlayerFactory) {

    @Transactional
    fun applyMatchResult(dto: List<PlayerPointDto>): String {
        val points = dto.map { PlayerPoint(it.playerId, GamePoint(it.point)) }
        val result = MatchResult(points)

        val players = result.points.map { point -> loadPlayer(point.playerId) }
        players.forEach { player -> player.applyMatchResult(result) }
        players.forEach { player -> playerRepository.save(player) }

        return "ok"
    }

    private fun loadPlayer(playerId: UUID) =
            playerRepository.loadWithLock(playerId) ?: playerFactory.createAccount(playerId)


}