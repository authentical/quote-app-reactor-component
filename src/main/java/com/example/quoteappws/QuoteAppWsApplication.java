package com.example.quoteappws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.Environment;
import reactor.bus.Event;
import reactor.bus.EventBus;


import static reactor.bus.selector.Selectors.$;


@Configuration
@EnableAutoConfiguration    // todo why not @SpringBootApplication
@ComponentScan
public class QuoteAppWsApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(QuoteAppWsApplication.class, args);
	}


	@Autowired
	private EventBus eventBus;

	@Autowired // todo This is @Service...why is it be Autowired
	private NotificationConsumer notificationConsumer;

	// todo Environments are created and terminated by the reactor user ...
	//  It IS a thread pool?
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

		// on method params:   final Selector selector, final Consumer<T> consumer final Consumer<T> consumer
		eventBus.on($("notificationConsumer"), notificationConsumer);
	}

}

