package com.vahid.board.match.domain

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory


class ValidationUtil {
    companion object{
        private val factory: ValidatorFactory
        private val validator: Validator

        init {
            factory = Validation.buildDefaultValidatorFactory()
            validator = factory.validator
        }

        fun validateOrThrow(obj: Any){
            val violations =  validator.validate(obj)
            if (violations.isNotEmpty()){
                throw ValidationError(violations)
            }
        }
    }
}