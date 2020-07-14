package com.spring.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.util.Properties;

import static com.spring.application.constant.SettingNames.*;

/**
 * Класс конфигурации JPA + JDBC
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.spring.application.repository")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan("com.spring.application")
public class JPAConfig {

    @Value("${url}")
    private String url;

    @Value("${driverClassName}")
    private String driverClassName;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddl;

    @Value("${hibernate.dialect}")
    private String dialect;

    /**
     * Метод описывающий настройки подключения к БД(настройки JDBC)
     * @return DataSource
     */
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * Метод создающий фабрику для менеджера сущностей
     * @param dataSource настройки подключения к БД
     * @return EntityManagerFactory
     */
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory getEntityManager(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource);
        entityManager.setJpaProperties(getProperties());
        entityManager.setPackagesToScan("com.spring.application.entity");
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return entityManager.getObject();
    }

    /**
     * Метод для формирования настроек Hibernate
     * @return Properties
     */
    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put(HibernateHBM2DDLAuto, hbm2ddl);
        properties.put(HibernateDialect, dialect);
        return properties;
    }

    /**
     * Метод создающий менеджер транзакций
     * @param entityManagerFactory фабрика менеджера сущностей
     * @return PlatformTransactionManager
     */
    @Bean(name = "transactionManager")
    public PlatformTransactionManager getTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
