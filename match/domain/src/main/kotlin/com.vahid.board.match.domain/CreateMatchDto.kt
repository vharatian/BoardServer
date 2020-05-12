package com.vahid.board.match.domain

import java.util.*
import javax.validation.constraints.AssertFalse
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Positive

class CreateMatchDto(
        val creatorId: UUID,
        @Positive
        val entranceCost: Int,
        @NotEmpty
        val gustsId: Set<UUID>
) {
    init {
        ValidationUtil.validateOrThrow(this)
    }

    @AssertFalse
    fun isCreatorInGusts(): Boolean {
        return gustsId.contains(creatorId)
    }
}