package com.vahid.board.match.infrastructure

import com.vahid.board.match.domain.Money
import com.vahid.board.match.domain.MoneyFactory
import org.springframework.stereotype.Component

@Component("MatchMoneyFactory")
class JpaMoneyFactory: MoneyFactory() {
    override fun newInstance(amount: Int): Money {
        return JpaMoney(amount)
    }
}