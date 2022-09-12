package br.com.jaqueline.sample.step.reader;

import br.com.jaqueline.sample.model.Customer;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class CustomerItemReader {
    
    @StepScope
    @Bean
    public FlatFileItemReader<Customer> customerReader(
            @Value("#{jobParameters['fileIn']}") Resource file) {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("customerReader")
                .resource(file)
                .fixedLength()
                .columns(getRanges())
                .names(getNames())
                .targetType(Customer.class)
                .build();
    }

    private Range[] getRanges() {
        return new Range[]{
                new Range(1, 10),
                new Range(11, 20),
                new Range(21, 23),
                new Range(24, 43)
        };
    }

    private String[] getNames() {
        return new String[]{
                "name",
                "lastName",
                "age",
                "email"
        };
    }

}