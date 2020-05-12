package com.vahid.board.score.domain

import javax.validation.constraints.PositiveOrZero

interface Money{

    companion object{
        fun newInstance(amount: Int): Money {
            return DiUtils.getBean(MoneyFactory::class.java).createValidMoney(amount)
        }
    }

    val amount: Int
        @PositiveOrZero
        get

    operator fun compareTo(other: Money): Int {
        return amount.compareTo(other.amount)
    }

    operator fun minus(other: Money): Money {
        return newInstance(amount - other.amount)
    }

    operator fun plus(other: Money): Money {
        return newInstance(amount + other.amount)
    }
}
