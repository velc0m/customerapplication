package com.vitali.cloud.jlong.customerapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class CustomerService {

    private final JdbcTemplate jdbcTemplate;

    public Collection<Customer> findAll() {
        RowMapper<Customer> rowMapper = (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("email"));
        return jdbcTemplate.query("select * from Customers", rowMapper);
    }
}
