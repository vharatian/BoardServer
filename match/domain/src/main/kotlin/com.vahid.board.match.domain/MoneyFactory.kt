package com.vahid.board.match.domain

abstract class MoneyFactory {

    fun newMoney(amount: Int): Money{
        val money = newInstance(amount)
        ValidationUtil.validateOrThrow(money)
        return money
    }

    protected abstract fun newInstance(amount: Int): Money
}