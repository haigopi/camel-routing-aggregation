package com.example.camel.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Tenant {
    private String name;
    private List<Owner> owners = new ArrayList<>();
    private String message;
}
