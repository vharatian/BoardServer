package com.vahid.board.match.infrastructure

import com.vahid.board.match.domain.GamePoint
import com.vahid.board.match.domain.PlayerPoint
import com.vahid.board.match.domain.PlayerPointDto
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "player_point")
class JpaPlayerPoint(): JpaEntity(), PlayerPoint {
    override var confirmed: Boolean = false

    @Type(type="pg-uuid")
    override lateinit var playerId: UUID

    @Embedded
    override lateinit var point: GamePoint

    constructor(dto: PlayerPointDto): this(){
        playerId = dto.playerId
        point = GamePoint.newInstance(dto.point)
    }
}