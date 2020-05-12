package com.vahid.board.match.domain

abstract class GamePointFactory {
    fun newInstance(value: Int): GamePoint {
        val point = this.newImplInstance(value)
        ValidationUtil.validateOrThrow(value)
        return point
    }

    protected abstract fun newImplInstance(value: Int): GamePoint
}