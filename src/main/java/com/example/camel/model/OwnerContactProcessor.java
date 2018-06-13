package com.example.camel.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@Slf4j
public class OwnerContactProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Tenant tenant = exchange.getIn().getBody(Tenant.class);

        // Start communicating wth owers -- Up to your cretivity to handle further routes ..


        log.info("OwnerContactProcessor is now competed its business.");
    }

    @PostConstruct
    public void init() {
        log.info("{} Created.", this.getClass().getSimpleName());
    }

}
