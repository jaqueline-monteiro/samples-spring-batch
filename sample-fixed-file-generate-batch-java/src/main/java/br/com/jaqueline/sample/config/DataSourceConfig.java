package br.com.jaqueline.sample.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.hikari.connection-timeout}")
    private Long connectionTimeout;
    @Value("${spring.datasource.hikari.minimum-idle}")
    private int minimumIdle;
    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maximumPoolSize;
    @Value("${spring.datasource.hikari.idle-timeout}")
    private Long idleTimeout;
    @Value("${spring.datasource.hikari.max-lifetime}")
    private Long maxLifetime;
    @Value("${spring.datasource.hikari.pool-name}")
    private String poolName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
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
