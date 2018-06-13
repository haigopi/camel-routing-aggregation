package com.example.camel.model;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
@Slf4j
public class Owner {
    String ownerId;

    @PostConstruct
    public void init() {
        log.info("{} Created.", this.getClass().getSimpleName());
    }
}
