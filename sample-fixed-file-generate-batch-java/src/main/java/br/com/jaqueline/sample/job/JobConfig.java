package br.com.jaqueline.sample.job;

import br.com.jaqueline.sample.listener.JobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   Step step, JobListener jobListener) {
        return jobBuilderFactory
                .get("job")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .build();
    }

}
