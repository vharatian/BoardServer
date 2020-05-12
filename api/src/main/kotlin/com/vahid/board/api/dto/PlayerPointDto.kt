package com.vahid.board.api.dto

import javax.validation.constraints.NotNull

class PlayerPointDto {

    @NotNull
    var playerId: Long? = null

    @NotNull
    var point: Int? = null

}
