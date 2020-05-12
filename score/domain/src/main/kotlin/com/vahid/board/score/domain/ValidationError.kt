package com.vahid.board.score.domain

import javax.validation.ConstraintViolation

class ValidationError(val violations: Set<ConstraintViolation<Any>>) : BusinessException() {
    override val message: String?
        get() {
            return violations.map { it.message }
                    .reduce{acc, message -> "$acc$message\n" }
        }
}
