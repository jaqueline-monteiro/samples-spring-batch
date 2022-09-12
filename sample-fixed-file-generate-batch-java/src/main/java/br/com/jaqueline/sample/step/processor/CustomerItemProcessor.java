package br.com.jaqueline.sample.step.processor;

import br.com.jaqueline.sample.model.Customer;
import br.com.jaqueline.sample.model.Telephone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {
    private static final Logger log = LoggerFactory.getLogger(CustomerItemProcessor.class);

    @Override
    public Customer process(Customer customer) throws Exception {
        log.info("Incrementing customer object to write into the file. ");
        Telephone telephone = new Telephone();
        telephone.setTelephone("21999711024");
        customer.setName(customer.getName().toUpperCase());
        customer.setLastName(customer.getLastName().toUpperCase());
        customer.setTelephone(telephone.getTelephone());
        return customer;
    }

}
