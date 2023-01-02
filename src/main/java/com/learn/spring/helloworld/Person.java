package com.learn.spring.helloworld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

record Address(String name, String firstLine, String secondLine, String state, String zipCode) {
};

// This is a spring configuration class
// This indicates that the class declares one or more bean methods. Keep in mind you are still
// writing code to create objects (like new ABC()...) inside a method annotated with @Bean
// How do we get Spring to create objects for us ? (@Component)
// Spring uses this configuration class and creates beans during start up or initialization
@Configuration
public class Person {

    // Indicates that the method produces a bean that is managed by the spring container
    @Bean("fullName")
    @Primary
    public String name() {
        return "Rahul Marigowda";
    }

    // @Qualifier takes more precedence over @Primary
    @Bean
    @Qualifier("alternateName")
    public String altName() {
        return "Rahul Marigowda";
    }

    @Bean
    public String employeeID() {
        return "AHJFGS678678";
    }

    @Bean
    public Address address() {
        return new Address(name(), "500 241st LN SE", "", "WA", "98074");
    }

    @Bean("alternateAddress")
    public Address backupAddress() {
        // Here the name() call would still return the singleton bean. When we make a name() call here, spring intercepts
        // and returns the name (fullName) object that was already created. We don't create new beans with every call
        return new Address(name(), "595 241st LN SE", "", "WA", "98074");
    }

    // In this case the fullName parameter is autowired. There is a bean already named fullName which
    // spring uses in the below function parameter. In such cases the parameter is auto wired based on the
    // same name. If the name differs,
    // There should be a single bean of that type or
    // There has to be a @Primary annotation on one the same type beans or
    // We have to qualify any same type bean and use that in the parameter as shown in addressByInjectingOtherBeansWithQualifier
    @Bean
    public Address addressByInjectingOtherBeans(String fullName) {
        return new Address(fullName, "595 241st LN SE", "", "WA", "98074");
    }

    // If you don't want to use the default or primary bean, you can qualify a bean and then use the
    // @Qualifier annotation as shown below in the parameter you want to autowire
    @Bean
    public Address addressByInjectingOtherBeansWithQualifier(@Qualifier("alternateName") String name) {
        return new Address(name, "595 241st LN SE", "", "WA", "98074");
    }
}
