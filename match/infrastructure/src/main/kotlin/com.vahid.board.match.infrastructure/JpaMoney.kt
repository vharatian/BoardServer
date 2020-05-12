package com.vahid.board.match.infrastructure

import com.vahid.board.match.domain.Money
import javax.persistence.Embeddable

@Embeddable
class JpaMoney() : Money {

    constructor(amount: Int) : this() {
        this.amount = amount
    }

    override var amount: Int = -1
}