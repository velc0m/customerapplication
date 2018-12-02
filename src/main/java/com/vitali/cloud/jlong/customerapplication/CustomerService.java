package com.vitali.cloud.jlong.customerapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerService {

    private final DataSource dataSource;

    public Collection<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            try (ResultSet rs = statement.executeQuery("select * from CUSTOMERS")) {
                while (rs.next()) {
                    customers.add(new Customer(rs.getLong("ID"), rs.getString("EMAIL")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }
}
