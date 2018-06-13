package com.example.camel.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class URLTransformer implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Tenant tenant = exchange.getIn().getBody(Tenant.class);
        String owner = (String)exchange.getIn().getHeader("OWNER_ID");
        // Process & Update Tenant as needed
        setCorrectHeaders(exchange);
        log.info("{}  URL Transformation is success", owner);
    }

    public void setCorrectHeaders(Exchange exchange) {
        final Object obj = exchange.getIn().getBody();
        exchange.getOut().setBody(obj);
        Map<String, Object> headers = exchange.getIn().getHeaders();
        exchange.getOut().setHeaders(headers);
    }

    @PostConstruct
    public void init(){
        log.info("{} Created.", this.getClass().getSimpleName());
    }
}
