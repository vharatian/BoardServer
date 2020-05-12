package com.vahid.board.match.infrastructure

import com.vahid.board.match.domain.MatchResult
import com.vahid.board.match.domain.ScoreServiceAdapter
import com.vahid.board.score.application.PlayerPointDto
import com.vahid.board.score.application.ScoreService
import org.springframework.stereotype.Component

@Component
class ScoreServiceAdapterImpl(
        private val scoreService: ScoreService
): ScoreServiceAdapter {
    override fun applyResultScore(matchResult: MatchResult) {
        val dto = matchResult.points.map { PlayerPointDto(it.playerId, it.point.value) }
        scoreService.applyMatchResult(dto)
    }
}