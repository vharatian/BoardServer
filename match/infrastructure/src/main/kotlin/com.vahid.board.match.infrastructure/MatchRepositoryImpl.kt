package com.vahid.board.match.infrastructure

import com.vahid.board.match.domain.Match
import com.vahid.board.match.domain.MatchFactory
import com.vahid.board.match.domain.MatchRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MatchRepositoryImpl(
        matchFactory: MatchFactory,
        private val jpaMatchRepository: JpaMatchRepository
) : MatchRepository(matchFactory) {
    override fun saveMatch(match: Match) {
        jpaMatchRepository.save(match as JpaMatch)
    }

    override fun loadMatchAndLock(id: UUID): Match? {
        return jpaMatchRepository.findMatchById(id)
    }
}