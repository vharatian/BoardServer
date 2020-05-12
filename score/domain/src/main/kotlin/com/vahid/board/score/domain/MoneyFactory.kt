package com.vahid.board.score.domain

import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
abstract class MoneyFactory {

    fun createValidMoney(amount: Int): Money{
        val money = createMoney(amount)

        ValidationUtil.validateOrThrow(money)

        return money
    }

    abstract fun createMoney(amount: Int): Money
}