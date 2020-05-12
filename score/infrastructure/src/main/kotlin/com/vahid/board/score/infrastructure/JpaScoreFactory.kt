package com.vahid.board.score.infrastructure

import com.vahid.board.score.domain.Score
import com.vahid.board.score.domain.ScoreFactory

class JpaScoreFactory : ScoreFactory() {
    override fun newScore(value: Double): Score {
        return JpaScore(value)
    }

}