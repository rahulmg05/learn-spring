package com.learn.spring.DifferentTypesOfDI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
class Dependency1 {
    public String toString() {
        return "Dependency1";
    }
}

@Component
class Dependency2 {
    public String toString() {
        return "Dependency2";
    }
}

@Component("svc")
class Service {
    // Field injection
    // @Autowired
    Dependency1 dependency1;
    // @Autowired
    Dependency2 dependency2;

    // Constructor injection - Autowired annotation is not mandatory
    // Constructor injection is preferred over other types of DI as
    // all code related to DI is in one method.
//    @Autowired
//    public Service(Dependency1 dependency1, Dependency2 dependency2) {
//        this.dependency1 = dependency1;
//        this.dependency2 =dependency2;
//    }

    public String toString() {
        return "Dependencies are: " + dependency1 + " and " + dependency2;
    }

    public Dependency1 getDependency1() {
        return dependency1;
    }

    @Autowired
    public void setDependency1(Dependency1 dependency1) {
        this.dependency1 = dependency1;
    }

    public Dependency2 getDependency2() {
        return dependency2;
    }

    @Autowired
    public void setDependency2(Dependency2 dependency2) {
        this.dependency2 = dependency2;
    }
}

/* There are 3 types of DI
1. Constructor injection
2. Setter injection
3. Field injection

Refer section 3 of https://www.udemy.com/course/spring-boot-and-spring-framework-tutorial-for-beginners
Step 06, Step 05 and 04 are good for that is written here
*/

@ComponentScan
public class Application {
    public static void main (String[] args) {

        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class)) {
            System.out.println(ctx.getBean("svc"));
        }
    }
}
