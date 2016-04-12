// @formatter:off
package com.myapp.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring boot main class. Its class name need to be packaged to let Spring boot bootstrap process
 * be aware of.
 *
 * Maven spring boot plugin can take its name from maven property(start-class)
 *
 *
 */
@ComponentScan(value={"com.myapp"})
@EnableAutoConfiguration(exclude={
    DataSourceAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
})
public class ExampleApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ExampleApp.class);
        app.setShowBanner(false);
        app.run(args);
    }
}
