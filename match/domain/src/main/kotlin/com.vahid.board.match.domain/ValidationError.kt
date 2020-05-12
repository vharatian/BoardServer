package com.vahid.board.match.domain

import javax.validation.ConstraintViolation

class ValidationError(val violations: Set<ConstraintViolation<Any>>) : BusinessException(createMessage(violations)) {

    companion object {
        fun createMessage(violations: Set<ConstraintViolation<Any>>): String {
            return violations.map { it.message }
                    .reduce{acc, message -> "$acc$message\n" }
        }
    }

}
