package com.example.camel.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@Slf4j
public class PayloadTransformer implements Processor
{

    @Override
    public void process(Exchange exchange) throws Exception {
        Tenant tenant = exchange.getIn().getBody(Tenant.class);
        UUID uuid = UUID.randomUUID();

        exchange.getOut().setHeader("ID", uuid);
        log.info("Payload Transformation is success");
    }

    @PostConstruct
    public void init() {
        log.info("{} Created.", this.getClass().getSimpleName());
    }
}
