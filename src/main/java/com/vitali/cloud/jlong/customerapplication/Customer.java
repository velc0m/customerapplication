package com.vitali.cloud.jlong.customerapplication;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
class Customer {

    Customer(long id, String email) {
        this.id = id;
        this.email = email;
    }

    @Id
    @GeneratedValue
    private long id;

    private String email;
}
