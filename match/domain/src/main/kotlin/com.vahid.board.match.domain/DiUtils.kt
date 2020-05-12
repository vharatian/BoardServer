package com.vahid.board.match.domain

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Service

@Service("MatchDiUtils")
class DiUtils: ApplicationContextAware{

    companion object{
        lateinit var applicationContext: ApplicationContext

        fun <T> getBean(clazz: Class<T>): T{
            return applicationContext.getBean(clazz)
        }
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        DiUtils.applicationContext = applicationContext
    }

}