package br.com.jaqueline.sample.step;

import br.com.jaqueline.sample.listener.StepListener;
import br.com.jaqueline.sample.model.Customer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepConfig {

    @Value("${max.chunk}")
    private int maxChunk;

    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory,
                     StepListener stepListener,
                     ItemReader<Customer> customerReader,
                     ItemProcessor<Customer, Customer> customerProcessor,
                     ItemWriter<Customer> customerWriter) {
        return stepBuilderFactory
                .get("step")
                .listener(stepListener)
                .<Customer, Customer>chunk(maxChunk)
                .reader(customerReader)
                .processor(customerProcessor)
                .writer(customerWriter)
                .build();
    }

}
