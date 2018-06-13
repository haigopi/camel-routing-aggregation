package com.example.camel.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RouteSurprises {

    @Handler
    public void handleError(Exchange exchange) {
        log.info("Place to handle the error");
    }
}
