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

        // Persist to DB
        // Obtain a connection and save to your custom DB.
        // Don;t forget this one is a spring component.
        tenant.setId(uuid);
        exchange.getOut().setBody(tenant);
        exchange.getOut().setHeader("ID", uuid); // for the sake to controller that receives back

    }
}
