package net.rockshore.prototype.threading.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FlightMessageAssociationService {

	private ExecutorService executorService;
	private Callable<Object> callableFlightAssociator;
	BlockingQueue<FlightAltitudeUpdate> inputQueue;
	BlockingQueue<FlightAltitudeUpdate> outputQueue;

	// list of flights we have seen (ie, whether we should create or update)
	private static final ConcurrentMap<String, Long> flightList = new ConcurrentHashMap<String, Long>(500, 0.9f, 2);

	public FlightMessageAssociationService(int numberOfThreads,
			BlockingQueue<FlightAltitudeUpdate> inputQueue,
			BlockingQueue<FlightAltitudeUpdate> outputQueue) {

		super();
		
		this.inputQueue = inputQueue;
		this.outputQueue = outputQueue;

		// Create an executorService to manage our threads for us
		executorService = Executors.newFixedThreadPool(numberOfThreads);

		// Create an instance of our 'callable' object that will be run on
		// managed threads to do work for us - in this case, generate test
		// messages and place them on the output blockingQueue.
		callableFlightAssociator = new FlightMessageAssociator(flightList,
				inputQueue, outputQueue);
	}

	public void start() {

		//while(!inputQueue.isEmpty()) {
		while(true) {
			executorService.submit(callableFlightAssociator);
		}
	}

}