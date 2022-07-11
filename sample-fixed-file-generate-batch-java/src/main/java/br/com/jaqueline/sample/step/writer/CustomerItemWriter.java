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
                    .resource(getFile())
                    .shouldDeleteIfExists(true)
                    .headerCallback(getHeader())
                    .formatted()
                    .format(getFormat())
                    .names(getNames())
                    .build();
    }

    private Resource getFile() {
        Resource file = new FileSystemResource(outputResource + fileName);
        return file;
    }

    private FlatFileHeaderCallback getHeader() {
        String spaceBetweenColumns = "\t";
        return writer -> {
            writer.append("Name");
            writer.append(spaceBetweenColumns.repeat(2));
            writer.append("LastName");
            writer.append(spaceBetweenColumns.repeat(2));
            writer.append("Age");
            writer.append(spaceBetweenColumns.repeat(2));
            writer.append("E-mail");
            writer.append(spaceBetweenColumns.repeat(6));
            writer.append("Telephone");
            writer.append(spaceBetweenColumns.repeat(2));
            writer.append("Address");
        };
    }

    private String getFormat() {
        return "%-11s %-15s %-7s %-27s %-15s %-49s";
    }

    private String[] getNames() {
        return new String[]{"name", "lastName", "age", "email", "telephone", "address"};
    }

}
