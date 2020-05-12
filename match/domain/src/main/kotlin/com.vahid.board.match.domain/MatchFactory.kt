package com.vahid.board.match.domain

import org.springframework.stereotype.Service
import java.util.*

abstract class MatchFactory(
        private val moneyServiceAdapter: MoneyServiceAdapter,
        private val scoreServiceAdapter: ScoreServiceAdapter
) {
    fun createInstance(creatorId: UUID, entranceCost: Money): MatchAggregate {
        val match = newInstance(UUID.randomUUID(), creatorId, entranceCost)

        ValidationUtil.validateOrThrow(match)

        return createAggregate(match)
    }

    fun createAggregate(match: Match): MatchAggregate {
        return MatchAggregate(match, moneyServiceAdapter, scoreServiceAdapter)
    }

    protected abstract fun newInstance(key: UUID, creatorId: UUID, entranceCost: Money): Match
}