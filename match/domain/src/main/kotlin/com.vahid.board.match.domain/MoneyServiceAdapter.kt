package com.vahid.board.match.domain

import java.util.*

abstract class MoneyServiceAdapter {
    abstract fun withdraw(playerId: UUID, amount: Money)
    abstract fun deposit(playerId: UUID, amount: Money)
}
