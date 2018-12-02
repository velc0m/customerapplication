package com.vitali.cloud.jlong.customerapplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfiguration {

    @Bean(destroyMethod = "shutdown")
    DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setName("customers").setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public CustomerService customerService(DataSource dataSource) {

        /*DataSourceInitializer init = new DataSourceInitializer();
        init.setDataSource(dataSource);

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setScripts(new ClassPathResource("schema.sql"), new ClassPathResource("data.sql"));

        init.setDatabasePopulator(populator);
        init.afterPropertiesSet();*/
        return new CustomerService(dataSource);
    }
}
