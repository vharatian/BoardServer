package com.vahid.board.score.infrastructure

import com.vahid.board.score.domain.Money
import com.vahid.board.score.domain.Player
import com.vahid.board.score.domain.Score
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "player", indexes = [Index(name = "player_id_name", columnList = "id")])
class JpaPlayer() : Player, JpaEntity() {

    override lateinit var id: UUID

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "value", column = Column(name = "score_value"))
    )
    lateinit var _score: JpaScore

    override var score: Score
        @Transient
        get() = _score
        set(value) {
            _score = value as JpaScore
        }

    @Embedded
    @AttributeOverrides(
            AttributeOverride(name = "amount", column = Column(name = "balance_amount"))
    )
    lateinit var _balance: JpaMoney

    override var balance: Money
        @Transient
        get() = _balance
        set(value) {
            this._balance = value as JpaMoney
        }

    constructor(id: UUID) : this() {
        this.id = id
    }
}
