package com.example.camel.config;

import com.example.camel.model.*;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Configuration
public class CamelRouters extends RouteBuilder {

    @Autowired
    private RouteSurprises routeSurprises;

    @Autowired
    private SaveToDB saveToDB;

    @Autowired
    private DynamicRouter dynamicRouter;

    @Autowired
    protected OwnerContactProcessor ownerContactProcessor;

    @Autowired
    private LoadOwnerConfigs loadOwnerConfigs;

    @Autowired
    private URLTransformer urlTransformer;

    @Autowired
    private PayloadTransformer payloadTransformer;

    @PostConstruct
    public void init() {
        log.info("Routes Loaded");
    }

    @Override
    public void configure() throws Exception {

        //onException(ExampleException.class).handled(true).bean(routeSurprises).stop();

        from("direct:REST").process(saveToDB).dynamicRouter(method(dynamicRouter));

        from("direct:TENANT").threads(4, 8, "[*G*T]")
                .process(loadOwnerConfigs)
                .process(urlTransformer)
                .process(payloadTransformer)
                .aggregate(header("AGGREGATOR"), new ConcurrentAggregationStrategy()).completionSize(header("TOTAL_OWNERS_COUNT"))
                .completionTimeout(1000)
                .log("${header.AGGREGATOR.size()} out of ${header.TOTAL_OWNERS_COUNT.size()} completed")
                .process(ownerContactProcessor)
                .log("Dynamic Route processing completed");

    }
}
