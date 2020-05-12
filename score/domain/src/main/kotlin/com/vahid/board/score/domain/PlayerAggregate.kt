package com.vahid.board.score.domain

class PlayerAggregate internal constructor(internal val player: Player) {

    fun applyMatchResult(result: MatchResult){
        player.score += calculateScore(result)
    }

    private fun calculateScore(info: MatchResult): Score {
        val totalPoint = info.points
                .fold(GamePoint(0)) { sum, pp -> sum + pp.point }

        val point = info.points.single { it.playerId == player.id }
        return Score.newInstance(point.point / totalPoint)
    }

    fun withdraw(amount: Money) {
        if (player.balance < amount){
            throw BusinessException("Not enough coins")
        }

        player.balance -= amount
    }

    fun deposit(amount: Money) {
        player.balance += amount
    }
}