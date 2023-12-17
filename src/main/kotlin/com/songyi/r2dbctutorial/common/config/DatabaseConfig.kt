package com.songyi.r2dbctutorial.common.config

import com.songyi.r2dbctutorial.R2dbcTutorialApplication
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@Configuration
@EnableR2dbcRepositories(basePackageClasses = [R2dbcTutorialApplication::class])
class DatabaseConfig {

    @Bean
    fun initializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer {
        val initializer = ConnectionFactoryInitializer()
        val resourceDatabasePopulator = ResourceDatabasePopulator(ClassPathResource("sql/schema.sql"))
        initializer.setConnectionFactory(connectionFactory)
        initializer.setDatabasePopulator(resourceDatabasePopulator)
        return initializer
    }
}
