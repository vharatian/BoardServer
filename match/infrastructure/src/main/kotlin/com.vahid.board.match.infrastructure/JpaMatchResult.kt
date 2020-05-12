package com.vahid.board.match.infrastructure

import com.vahid.board.match.domain.MatchResult
import com.vahid.board.match.domain.MatchResultDto
import com.vahid.board.match.domain.PlayerPoint
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Embeddable
import javax.persistence.OneToMany

@Embeddable
class JpaMatchResult(): MatchResult {
    @Type(type="pg-uuid")
    override lateinit var reporterId: UUID

    @OneToMany
    override lateinit var points: List<JpaPlayerPoint>

    constructor(dto: MatchResultDto): this(){
        reporterId = dto.reporterId
        points = dto.points.map { JpaPlayerPoint(it) }
    }
}