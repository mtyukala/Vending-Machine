package com.vending.machine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
//@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan(basePackages = "com.vending.machine")
public class BeanConfig {
    @Bean
    public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(new DataSourceConfig().dataSource());
        sessionFactory.setPackagesToScan("com.vending.machine");
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        factory.setPackagesToScan("com.vending.machine");
        try {
            factory.setDataSource(new DataSourceConfig().dataSource());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return factory;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManagement() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject( ));
        return manager;

    }


    private Properties getHibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2dll.auto", "update");
                setProperty("hibernate.dialet", "org.hibernate.dialect.PostgreSQLDialect");
                setProperty("hibernate.show_sql", "true");
            }
        };
    }

}
