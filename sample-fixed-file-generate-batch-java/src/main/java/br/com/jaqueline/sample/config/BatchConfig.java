package br.com.jaqueline.sample.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class BatchConfig extends DefaultBatchConfigurer {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Override
    protected JobLauncher createJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(createJobRepository());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

    @Override
    protected JobRepository createJobRepository() throws Exception {
        JobRepositoryFactoryBean jobRepository = new JobRepositoryFactoryBean();
        jobRepository.setDataSource(dataSource);
        jobRepository.setTransactionManager(platformTransactionManager);
        jobRepository.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
        jobRepository.setTablePrefix("TESTE.BATCH_");
        jobRepository.setMaxVarCharLength(1000);
        jobRepository.afterPropertiesSet();
        return jobRepository.getObject();
    }

    @Override
    protected JobExplorer createJobExplorer() throws Exception {
        JobExplorerFactoryBean jobExplorer = new JobExplorerFactoryBean();
        jobExplorer.setDataSource(dataSource);
        jobExplorer.setTablePrefix("TESTE.BATCH_");
        jobExplorer.afterPropertiesSet();
        return jobExplorer.getObject();
    }

}
