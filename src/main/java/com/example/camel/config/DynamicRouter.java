package com.example.camel.config;

import com.example.camel.model.Owner;
import com.example.camel.model.Tenant;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.*;
import org.apache.camel.builder.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class DynamicRouter {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private CamelContext camelContext;

    @Handler
    public String route(Exchange exchange, @Header(Exchange.SLIP_ENDPOINT) String previousEndpoint) {
        return whereToGo(exchange);
    }

    private String whereToGo(Exchange exchange) {
        UUID id = (UUID) exchange.getIn().getHeader("ID");
        log.info("ID created in previous endpoint(SaveDB)", id);

        Tenant tenant = exchange.getIn().getBody(Tenant.class);
        List<Owner> owners = tenant.getOwners();

        Map<String, Object> headers = exchange.getIn().getHeaders();
        headers.put("AGGREGATOR", id);
        headers.put("TOTAL_OWNERS_COUNT", owners.size());

        for (Owner owner : owners) {

            Map<String, Object> headers_new = new HashMap<>();
            headers_new.putAll(headers);
            Exchange newExchange = ExchangeBuilder.anExchange(camelContext).withBody(tenant).build();
            newExchange.getIn().setHeaders(headers_new);
            newExchange.getOut().setHeaders(headers_new);
            producerTemplate.asyncSend("TENANT", newExchange);

        }

        return null;

    }

}
