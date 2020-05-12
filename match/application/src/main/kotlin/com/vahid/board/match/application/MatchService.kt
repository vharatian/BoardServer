package com.vahid.board.match.application

import com.vahid.board.match.domain.*
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class MatchService(
        private val matchFactory: MatchFactory,
        private val matchRepository: MatchRepository
) {

    private fun loadOrThrow(matchId: UUID): MatchAggregate {
        return matchRepository.loadWithLock(matchId)
                ?: throw BusinessException("Can not find match $matchId")
    }

    fun createMatch(dto: CreateMatchDto) {
        val match = matchFactory.createInstance(dto.creatorId, Money.newInstance(dto.entranceCost))

        match.chargeCreationCost()
        dto.gustsId.forEach { gustId -> match.inviteGust(gustId) }

        matchRepository.save(match)
    }

    fun acceptInvitation(matchId: UUID, gustId: UUID) {
        val match = loadOrThrow(matchId)
        match.joinPlayer(gustId)
        matchRepository.save(match)
    }

    fun reportResult(matchId: UUID, dto: MatchResultDto) {
        val match = loadOrThrow(matchId)
        match.reportResult(dto)
        matchRepository.save(match)
    }

    fun confirmResult(matchId: UUID, playerId: UUID) {
        val match = loadOrThrow(matchId)
        match.confirmResult(playerId)
        matchRepository.save(match)
    }


}