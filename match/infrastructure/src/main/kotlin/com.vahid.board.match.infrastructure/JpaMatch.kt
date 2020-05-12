package com.vahid.board.match.infrastructure

import com.vahid.board.match.domain.Match
import com.vahid.board.match.domain.MatchResult
import com.vahid.board.match.domain.MatchResultDto
import com.vahid.board.match.domain.Money
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "match", indexes = [Index(columnList = "id", unique = true, name = "match_key_index")])
class JpaMatch() : JpaEntity(), Match {

    @Type(type = "pg-uuid")
    @Column(name = "id")
    override lateinit var id: UUID

    @Type(type = "pg-uuid")
    override lateinit var creatorId: UUID

    @AttributeOverrides(
            AttributeOverride(name = "amount", column = Column(name = "entrance_cost_amount"))
    )
    lateinit var _entranceCost: JpaMoney

    override var entranceCost: Money
        @Transient
        get() = _entranceCost
        set(value) {
            _entranceCost = value as JpaMoney
        }


    @ElementCollection
    override lateinit var joinedPlayersId: Set<UUID>

    @ElementCollection
    override lateinit var invitedPlayersId: Set<UUID>

    @Embedded
    var _matchResult: JpaMatchResult? = null

    override var matchResult: MatchResult?
        @Transient
        get() = _matchResult
        set(value) {
            _matchResult = value as JpaMatchResult?
        }

    override fun createResult(dto: MatchResultDto): MatchResult {
        return JpaMatchResult(dto)
    }


    constructor(id: UUID, creatorId: UUID, entranceCost: Money) : this() {
        this.id = id
        this.creatorId = creatorId
        this.entranceCost = entranceCost
    }
}