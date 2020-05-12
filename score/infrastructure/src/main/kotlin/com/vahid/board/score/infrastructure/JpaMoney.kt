package com.vahid.board.score.infrastructure

import com.vahid.board.score.domain.Money
import javax.persistence.Embeddable

@Embeddable
class JpaMoney(): Money {
    override var amount: Int = -1

    constructor(amount: Int): this(){
        this.amount = amount
    }
}