package com.kotlinspring.course.catalog.persistence.config

import jakarta.persistence.EntityManagerFactory
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.configuration.FluentConfiguration
import org.postgresql.ds.PGSimpleDataSource
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer
import org.springframework.context.annotation.*
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaDialect
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource

@Configuration
@Profile("dev")
@EnableTransactionManagement
@ComponentScan("com.kotlinspring.course.catalog.persistence")
@EnableJpaRepositories("com.kotlinspring.course.catalog.persistence")
class PersistenceTestConfig {

    val dbUrl: String = System.getProperty("db.url")!!
    val dbUser: String = System.getProperty("db.user")!!
    val dbPassword: String = System.getProperty("db.password")!!
    val jpaShowSql: Boolean = false

    @Bean
    @Primary
    fun dataSource(): DataSource = (PGSimpleDataSource())
        .apply {
            this.setURL(dbUrl)
            this.user = dbUser
            this.password = dbPassword

//            this.validateConnectionOnBorrow = true
//            this.connectionFactoryClassName = "oracle.jdbc.pool.OracleDataSource"
//            this.initialPoolSize = 1
//            this.minPoolSize = 1
//            this.maxPoolSize = 10
        }

    @Bean
    fun flyway(fluentConfiguration: FluentConfiguration) = Flyway(fluentConfiguration)

    @Bean
    fun fluentConfiguration(dataSource: DataSource) = FluentConfiguration()
        .apply {
            this.dataSource(dataSource)
            this.locations("classpath:/db/migration")
            this.baselineOnMigrate(true)
            this.validateOnMigrate(true)
            this.cleanOnValidationError(true)
            this.encoding("UTF-8")
            this.baselineVersion("0")
        }

    @Bean
    fun flywayInitializer(flyway: Flyway) = FlywayMigrationInitializer(flyway)

    @Bean
    fun transactionManager(
        entityManagerFactory: EntityManagerFactory,
        hibernateJpaDialect: HibernateJpaDialect
    ) = JpaTransactionManager()
        .apply {
            this.entityManagerFactory = entityManagerFactory
            this.setJpaDialect(hibernateJpaDialect)
        }

    @Bean
    fun hibernateJpaDialect() = HibernateJpaDialect()

    @Bean
    fun entityManagerFactory(
        dataSource: DataSource,
        hibernateJpaVendorAdapter: HibernateJpaVendorAdapter
    ) = LocalContainerEntityManagerFactoryBean()
        .apply {
            this.setPackagesToScan("com.kotlinspring.course.catalog")
            this.dataSource = dataSource
            this.jpaVendorAdapter = hibernateJpaVendorAdapter
            this.setJpaProperties(jpaProperties())
        }

    private fun jpaProperties() = Properties().apply {
        this.setProperty("hibernate.show_sql", jpaShowSql.toString())
        this.setProperty("hibernate.jdbc.fetch_size", "250")
        this.setProperty("hibernate.jdbc.batch_size", "50")
        this.setProperty("hibernate.order_inserts", "true")
        this.setProperty("hibernate.order_updates", "true")
        this.setProperty("hibernate.generate_statistics", "false")
        this.setProperty("hibernate.format_sql", "false")
        this.setProperty("hibernate.jdbc.batch_versioned_data", "true")
        this.setProperty("hibernate.id.new_generator_mappings", "true")
    }

    @Bean
    fun hibernateJpaVendorAdapter() = HibernateJpaVendorAdapter()
        .apply {
            this.setGenerateDdl(false)
            this.setShowSql(jpaShowSql)
            this.setDatabase(Database.POSTGRESQL)
            //this.setDatabasePlatform("org.hibernate.dialect.Oracle12cDialect")
        }

    @Bean
    fun persistenceExceptionTranslationPostProcessor() = PersistenceExceptionTranslationPostProcessor()
}