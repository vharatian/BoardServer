package com.vahid.board.score.domain

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory


class ValidationUtil {
    companion object{
        private var factory: ValidatorFactory
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