package com.vahid.board.score.infrastructure

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity(name = "ScoreJpaEntity")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class JpaEntity {
    @Id
    @GeneratedValue
    open var dbId: Long = 0

    @CreationTimestamp
    var creationTime: Date? = null

    @UpdateTimestamp
    var lastUpdateTime: Date? = null

    @Version
    var version: Int? = null
}