package com.vahid.board.match.infrastructure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import java.util.*
import javax.sql.DataSource

const val PREFIX: String = "match"
const val PACKAGE = "com.vahid.board.$PREFIX"

@Configuration
@PropertySource("classpath:config/match-database.properties")
@EnableJpaRepositories(
        basePackages = [PACKAGE],
        entityManagerFactoryRef = "matchEntityManager",
        transactionManagerRef = "matchTransactionManager")
class MatchDatabaseConfiguration(private val env: Environment) {

    @Bean
    @ConfigurationProperties(prefix = "$PREFIX.datasource")
    fun matchDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean
    fun matchEntityManager(): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = matchDataSource()
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
    fun matchTransactionManager(): PlatformTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = matchEntityManager().getObject()
        return transactionManager
    }
}