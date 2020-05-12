package com.vahid.board.match.infrastructure

import com.vahid.board.match.domain.Match
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.LockModeType

@Repository
interface JpaMatchRepository: JpaRepository<JpaMatch, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    fun findMatchById(key:UUID): Match?
}
