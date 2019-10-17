package com.example.quoteappws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.Environment;
import reactor.bus.EventBus;


import static reactor.bus.selector.Selectors.$;


/*@Configuration
@EnableAutoConfiguration
@ComponentScan*/
@SpringBootApplication
public class QuoteAppWsApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(QuoteAppWsApplication.class, args);
	}



	@Autowired
	private EventBus eventBus;	//


	// My question about this @Autowired NotificationConsumer was answered by nickb on stackoverflow
	/* @Service is a specialization of @Component. It's an annotation that tells Spring to include
	this class as a bean in the Spring context. You can think of this as telling Spring what to
	pick up and put into the context during component scanning.

	@Autowired is Spring's annotation to inject something from the context.
	You can think of this as you declaring what you want to get out of Spring.
	In general, you need to use this annotation on any field, constructor, or setter
	that you want Spring to invoke to supply you with the object that it's managing for the given type.

	*/

	// If this isn't autowired, it won't belong to the application context and it won't
	// be able to accept() from the EventBus
	//@Autowired // Already @Service
	private NotificationConsumer notificationConsumer;



	// todo Environments are created and terminated by the reactor user ...
	//  It offers iterable thread pool?
	@Bean
	Environment env() {
		return Environment.initializeIfEmpty().assignErrorJournal();
	}

	@Bean
	EventBus createEventBus(Environment env) {
		return EventBus.create(env, Environment.THREAD_POOL);
	}

	@Override
	public void run(String[] args) throws Exception {

		/* The feature provides a type-safe mechanism to include constants(in our case it's $ attribute)
		into code without having to reference the class that originally defined the field.*/
		eventBus.on($("notificationConsumer"), notificationConsumer);
	}

}

