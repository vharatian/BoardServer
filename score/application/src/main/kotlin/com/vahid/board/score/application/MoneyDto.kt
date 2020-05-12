package com.vahid.board.score.application

import com.vahid.board.score.domain.Money

class MoneyDto(
        val amount: Int
){
    fun toMoney(): Money {
        return Money.newInstance(amount)
    }
}
