package com.example.camel.config;

import com.example.camel.model.SaveToDB;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CamelRouters extends RouteBuilder {

    @Autowired
    private RouteSurprises routeSurprises;

    @Autowired
    private SaveToDB saveToDB;

    @Autowired
    private DynamicRouter dynamicRouter;

    @PostConstruct
    public void init(){
        log.info("Routes Loaded");
    }

    @Override
    public void configure() throws Exception {

        onException(ExampleException.class).handled(true).bean(routeSurprises).stop();

        from("REST").process(saveToDB).dynamicRouter(method(dynamicRouter));

    }
}
