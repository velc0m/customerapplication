package com.vitali.cloud.jlong.customerapplication;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerService {

    private final DataSource dataSource = new EmbeddedDatabaseBuilder()
            .setName("customers").setType(EmbeddedDatabaseType.H2).build();

    public static void main(String[] args) {

        CustomerService customerService = new CustomerService();

        DataSource dataSource = customerService.dataSource;
        DataSourceInitializer init = new DataSourceInitializer();
        init.setDataSource(dataSource);

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setScripts(new ClassPathResource("schema.sql"), new ClassPathResource("data.sql"));

        init.setDatabasePopulator(populator);
        init.afterPropertiesSet();

        int size = customerService.findAll().size();
        Assert.isTrue(size == 2, "ok");
    }

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
