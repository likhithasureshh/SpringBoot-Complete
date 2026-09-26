package com.MultiThreading.LearnMultiThreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnMultiThreadingApplication {

	private static final Logger log = LoggerFactory.getLogger(LearnMultiThreadingApplication.class);

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(LearnMultiThreadingApplication.class, args);

		Thread workerThread = new Thread(()->
		{
			log.info("Executing workerThread");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
		workerThread.start();
		workerThread.join();
		log.info("Main thread");
	}

}
