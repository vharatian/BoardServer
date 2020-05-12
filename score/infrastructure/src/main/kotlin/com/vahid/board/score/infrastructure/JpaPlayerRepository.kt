package com.vahid.board.score.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.QueryHints
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.LockModeType
import javax.persistence.QueryHint

@Repository
interface JpaPlayerRepository: JpaRepository<JpaPlayer, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    fun findPlayerById(id: UUID): JpaPlayer?
}