package com.vahid.board.match.infrastructure

import com.vahid.board.match.domain.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class JpaMatchFactory(
        moneyServiceAdapter: MoneyServiceAdapter,
        scoreServiceAdapter: ScoreServiceAdapter) :
        MatchFactory(moneyServiceAdapter, scoreServiceAdapter
) {

    override fun newInstance(key: UUID, creatorId: UUID, entranceCost: Money): Match {
        return JpaMatch(key, creatorId, entranceCost)
    }
}