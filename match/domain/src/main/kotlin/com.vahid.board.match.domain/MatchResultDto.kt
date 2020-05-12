package com.vahid.board.match.domain

import java.util.*
import javax.validation.Valid
import javax.validation.constraints.AssertFalse
import javax.validation.constraints.AssertTrue

class MatchResultDto(
        val reporterId: UUID,
        val points: List<PlayerPointDto>
)
