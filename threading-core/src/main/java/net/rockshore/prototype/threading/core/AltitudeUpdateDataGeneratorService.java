package net.rockshore.prototype.threading.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.joda.time.DateTime;

/**
 * A service that is multi-threaded and generates test flight altitude update
 * messages for us and places them onto a blocking queue. Placing the messages
 * on the blocking queue means that we dont actually need to make use of the
 * 'callable' futures to be notified when each thread has produced some output.
 * The output will be placed on the blocking queue and taken off by the next
 * operation in the chain. If the queue fills to capacity it will block and the
 * generator threads will pause until there is space for more messages on the
 * queue.
 * 
 * @author neildunlop
 *
 */
public class AltitudeUpdateDataGeneratorService {

	private ExecutorService executorService;
	private Callable<Object> callableDataGenerator;
	private int numberOfMessagesToGenerate = 0;

	public AltitudeUpdateDataGeneratorService(int numberOfThreads,
			int numberOfMessagesToGenerate,
			BlockingQueue<FlightAltitudeUpdate> outputQueue) {

		super();
		this.numberOfMessagesToGenerate = numberOfMessagesToGenerate;

		// Create an executorService to manage our threads for us
		executorService = Executors.newFixedThreadPool(numberOfThreads);

		// Create an instance of our 'callable' object that will be run on
		// managed threads to do work for us - in this case, generate test
		// messages and place them on the output blockingQueue.
		callableDataGenerator = new AltitudeUpdateDataGenerator(outputQueue);
	}

	public void start() {

		// invokeAll blocks until the callable says it is done
		for (int i = 0; i < this.numberOfMessagesToGenerate; i++) {
			executorService.submit(callableDataGenerator);
		}
		System.out.println("-- DATA GENERATION DONE --");
		
		executorService.shutdown();
	}

}
