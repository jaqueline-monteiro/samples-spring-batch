package br.com.jaqueline.sample.step.writer;

import br.com.jaqueline.sample.model.Customer;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
public class CustomerItemWriter {

    @Value("${output.resource}")
    private String outputResource;

    @Value("${file.name}")
    private String fileName;

    @StepScope
    @Bean
    public FlatFileItemWriter<Customer> customerWriter() {
            return new FlatFileItemWriterBuilder<Customer>()
                    .name("customerWriter")
                    .resource(fileConfig())
                    .shouldDeleteIfExists(true)
                    .headerCallback(getHeader())
                    .formatted()
                    .format("%-11s %-15s %-7s %-26s")
                    .names(getNames())
                    .build();
    }

    private Resource fileConfig() {
        Resource file = new FileSystemResource(outputResource + fileName);
        return file;
    }

    private FlatFileHeaderCallback getHeader() {
        return writer -> {
            writer.append("Name\t\t");
            writer.append("LastName\t\t");
            writer.append("Age\t\t");
            writer.append("E-mail");
        };
    }

    private String[] getNames() {
        return new String[]{"name", "lastName", "age", "email"};
    }

}
