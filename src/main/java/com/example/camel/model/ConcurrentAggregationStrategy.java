package com.example.camel.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ConcurrentAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            ConcurrentHashMap map = new ConcurrentHashMap();
            newExchange.getIn().setHeader("AGGREGATED_MESSAGES", map);
        } else {
            ConcurrentHashMap map = (ConcurrentHashMap) oldExchange.getIn().getHeader("AGGREGATED_MESSAGES");
            newExchange.getIn().setHeader("AGGREGATED_MESSAGES", map);
        }

        return newExchange;
    }

    private void collectNeedInfo(ConcurrentHashMap map, Exchange exchange)
    {
        log.info("Collected Information on while aggregating..");
    }
}
