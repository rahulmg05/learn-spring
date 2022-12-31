package com.learn.spring.game;

import com.learn.spring.helloworld.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.learn.spring")
public class Application {
    public static void main(String[] args) {
        // AnnotationConfigApplicationContext should be passed a configuration class.
        // Spring checks the component scan annotation added in the configuration class
        // and creates all beans in the package and sub packages of the packages listed
        // in the component scan annotation
        try (AnnotationConfigApplicationContext ctx =
                     new AnnotationConfigApplicationContext(Application.class)) {
            // You can register other config classes (beans) using register method on the
            // spring container
            ctx.register(Person.class);
            ctx.getBean(GameRunner.class).run();
        }
    }
}
