package com.vahid.board.match.domain

import java.util.*

abstract class MatchRepository(
        val matchFactory: MatchFactory
) {

    fun loadWithLock(id: UUID): MatchAggregate? {
        val match = loadMatchAndLock(id)

        return if (match != null){
            matchFactory.createAggregate(match)
        }else{
            null
        }
    }

    fun save(proxy: MatchAggregate) {
        saveMatch(proxy.match)
    }


    protected abstract fun saveMatch(match: Match)
    protected abstract fun loadMatchAndLock(id: UUID): Match?
}
