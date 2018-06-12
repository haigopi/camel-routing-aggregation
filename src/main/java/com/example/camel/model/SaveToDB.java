package com.example.camel.model;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveToDB implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Tenant tenant = exchange.getIn().getBody(Tenant.class);
        UUID uuid = UUID.randomUUID();

        exchange.getOut().setHeader("ID", uuid);
        // Obtain a connection and save to your custom DB.
        // Don;t forget this one is a spring component.
    }
}
