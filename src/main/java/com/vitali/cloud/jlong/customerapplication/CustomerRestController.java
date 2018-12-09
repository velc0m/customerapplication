package com.vitali.cloud.jlong.customerapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RefreshScope
public class CustomerRestController {

    @Value("${project.message}")
    private String message;

    private final CustomerService customerService;

    @GetMapping("/customers")
    public Collection<Customer> getAll() {
        return customerService.findAll();
    }

    @GetMapping("/message")
    public String getRemoteMessage() {
        return message;
    }
}
