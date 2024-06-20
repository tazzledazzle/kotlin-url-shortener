package com.tschumacher.system.com.tschumacher.system.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
@ConfigurationProperties(prefix = "analytics.datasource")
class AnalyticsDataSourceConfig {

    @Bean
    fun dataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }
}