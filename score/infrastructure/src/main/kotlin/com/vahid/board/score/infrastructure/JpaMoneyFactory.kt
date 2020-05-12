package com.vahid.board.score.infrastructure

import com.vahid.board.score.domain.Money
import com.vahid.board.score.domain.MoneyFactory
import org.springframework.stereotype.Component

@Component
class JpaMoneyFactory: MoneyFactory() {
    override fun createMoney(amount: Int): Money {
        return JpaMoney(amount)
    }
}