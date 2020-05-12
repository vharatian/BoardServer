package com.vahid.board.score.infrastructure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import java.util.*
import javax.sql.DataSource

const val PREFIX: String = "score"
const val PACKAGE = "com.vahid.board.$PREFIX"

@Configuration
@PropertySource("classpath:config/score-database.properties")
@EnableJpaRepositories(
        basePackages = [PACKAGE],
        entityManagerFactoryRef = "scoreEntityManager",
        transactionManagerRef = "scoreTransactionManager")
class ScoreDatabaseConfiguration(private val env: Environment) {

    @Bean
    @ConfigurationProperties(prefix = "$PREFIX.datasource")
    fun scoreDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean
    fun scoreEntityManager(): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = scoreDataSource()
        em.setPackagesToScan(PACKAGE)
        val vendorAdapter = HibernateJpaVendorAdapter()
        em.jpaVendorAdapter = vendorAdapter
        val properties = HashMap<String, Any?>()
        properties["hibernate.hbm2ddl.auto"] = getConfig("hibernate.hbm2ddl.auto")
        properties["hibernate.dialect"] = getConfig("hibernate.dialect")
        properties["hibernate.default_schema"] = getConfig("hibernate.default_schema")
        em.setJpaPropertyMap(properties)
        return em
    }

    private fun getConfig(key: String): String {
        return env.getRequiredProperty("$PREFIX.$key")
    }

    @Bean
    fun scoreTransactionManager(): PlatformTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = scoreEntityManager().getObject()
        return transactionManager
    }
}