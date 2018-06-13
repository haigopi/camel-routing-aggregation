package com.example.camel.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
@Slf4j
public class Tenant {
    private String name;
    private List<Owner> owners = new ArrayList<>();
    private String message;
    @PostConstruct
    public void init(){
        log.info("{} Created.", this.getClass().getSimpleName());
    }
}
