package com.learn.spring.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Application {
	public static void main(String[] args) {

		// Launch a spring context
		/* What is a Bean ?
		   Java bean is simple terms is a java object with getters, setters and which
		   implements serializable interface.

		   What is an application context ?
		   Spring container manages spring beans and their life cycle.
		   Inside JVM you have a spring context which manages all spring beans. Spring
		   container is also called Spring IoC container or Spring/Application Context.
		   There are 2 types of containers. Bean factory and Application context. Bean
		   Factory has limited functionalities. Application Context is used widely (web
		   applications, easy integration with AOP). Bean factory is used where we are
		   severely constrained by memory.
		*/
		try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Person.class)) {
			// Configure things that we want spring to manage - @Configuration class
			// We did this by creating the Person class which is annotated using @Configuration

			// Retrieving the bean(s) managed by spring
			// The bean name in this case would be the name of the method that returns a bean
			System.out.println(ctx.getBean("fullName"));
			System.out.println(ctx.getBean("employeeID"));
			// You can have alternate names too by providing the name of the bean as a parameter
			// to the @Bean annotation
			System.out.println(ctx.getBean("alternateAddress"));
			System.out.println(ctx.getBean("addressByInjectingOtherBeans"));
			// You can get the bean by its type as well. Be careful with this as there can be more
			// than 1 bean with the same type.
			// In such cases it will fail with NoUniqueBeanDefinitionException.
			System.out.println(ctx.getBean(String.class));

			// Get all beans
			System.out.println("++++++++++Printing all beans++++++++++");
			Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
		}
	}
}
