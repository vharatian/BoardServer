package com.vahid.board.match.infrastructure

import com.vahid.board.match.domain.MoneyServiceAdapter
import com.vahid.board.match.domain.Money
import com.vahid.board.score.application.CoinService
import com.vahid.board.score.application.MoneyDto
import org.springframework.stereotype.Service
import java.util.*

@Service
class MoneyServiceAdapterImpl(val coinService: CoinService): MoneyServiceAdapter() {
    override fun withdraw(playerId: UUID, amount: Money) {
        coinService.withdraw(playerId, MoneyDto(amount.amount))
    }

    override fun deposit(playerId: UUID, amount: Money) {
        coinService.deposit(playerId, MoneyDto(amount.amount))
    }
}