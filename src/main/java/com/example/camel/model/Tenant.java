package com.example.camel.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Component
@Slf4j
public class Tenant {
    private UUID id;
    private String name;
    private List<Owner> owners = new ArrayList<>();
    private String message;
    @PostConstruct
    public void init(){
        log.info("{} Created.", this.getClass().getSimpleName());
    }
}
