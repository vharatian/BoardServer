package com.vahid.board.score.infrastructure

import com.vahid.board.score.domain.Score
import javax.persistence.Embeddable
import javax.persistence.Entity

@Embeddable
class JpaScore(): Score {

    constructor(value: Double): this(){
        this.value = value
    }

    override var value: Double = -1.0
}