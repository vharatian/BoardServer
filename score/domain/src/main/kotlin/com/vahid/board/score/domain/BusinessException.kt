package com.vahid.board.score.domain

open class BusinessException: Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
}