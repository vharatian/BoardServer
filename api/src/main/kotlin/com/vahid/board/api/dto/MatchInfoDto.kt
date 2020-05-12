package com.vahid.board.api.dto

import javax.validation.constraints.NotNull

class MatchInfoDto {

    @NotNull
    var coins: Int? = null

    @NotNull
    var points: List<PlayerPointDto>? = null

    @NotNull
    var winnerIndex: Int? = null

}
