package br.com.jaqueline.sample.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${teste.batch.datasource.hikari.connection-timeout}")
    private Long connectionTimeout;
    @Value("${teste.batch.datasource.hikari.minimum-idle}")
    private int minimumIdle;
    @Value("${teste.batch.datasource.hikari.maximum-pool-size}")
    private int maximumPoolSize;
    @Value("${teste.batch.datasource.hikari.idle-timeout}")
    private Long idleTimeout;
    @Value("${teste.batch.datasource.hikari.max-lifetime}")
    private Long maxLifetime;
    @Value("${teste.batch.datasource.hikari.pool-name}")
    private String poolName;
    @Value("${teste.batch.datasource.url}")
    private String url;
    @Value("${teste.batch.datasource.username}")
    private String username;
    @Value("${teste.batch.datasource.password}")
    private String password;
    @Value("${teste.batch.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setConnectionTimeout(connectionTimeout);
        config.setMinimumIdle(minimumIdle);
        config.setMaximumPoolSize(maximumPoolSize);
        config.setIdleTimeout(idleTimeout);
        config.setMaxLifetime(maxLifetime);
        config.setPoolName(poolName);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);
        return new HikariDataSource(config);
    }

}
