package net.rockshore.prototype.threading.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.joda.time.DateTime;

/**
 * Demonstration application that uses four threads to generate fake altitude
 * update events. The generation of the events can be simulated to take some
 * time. A 'Future' is used so that the producer thread can notify us when the
 * event has been created. We simply output the event to the console for now.
 * 
 * @author neildunlop
 *
 */

// This is all out of date. See App2 - making use of BlockingQueues rather than
// Futures
// NOT SURE IF THIS IS RIGHT!

// TODO: Make App 2 using executorService.invokeAll
// See:
// http://blog.carbonfive.com/2009/02/23/more-fun-with-java-concurrency-blockingqueue/

// Make an executor for the data generator to simulate multiple feeds
// Make an executor for the associator service with two threads (to make sure we
// dont have duplicate create commands and to check on the blocking on the
// concurrent collection of flightlist)
// Make an executor for domain updates. Should be able to pass a future so that
// we can check
// when the domain has been updated.
public class App {

	public static void main(String[] args) {

		// ExecutorService executorService = Executors.newFixedThreadPool(4);
		//
		// // create a list to hold the Future object associated with Callable -
		// // ie, how we get the results of the message generator
		// List<Future<FlightAltitudeUpdate>> altitudeUpdateMessageList = new
		// ArrayList<Future<FlightAltitudeUpdate>>();
		//
		// // Create MyCallable instance
		// Callable<FlightAltitudeUpdate> callable = new
		// AltitudeUpdateDataGenerator();
		//
		// for (int i = 0; i < 100; i++) {
		// // submit Callable tasks to be executed by thread pool
		// Future<FlightAltitudeUpdate> future = executorService
		// .submit(callable);
		// // add Future to the list, we can get return value using Future
		// altitudeUpdateMessageList.add(future);
		// }
		// for (int i = 0; i < altitudeUpdateMessageList.size(); i++) {
		//
		// Future<FlightAltitudeUpdate> altitudeUpdate =
		// altitudeUpdateMessageList
		// .get(i);
		//
		// try {
		// // print the return value of Future, notice the output delay in
		// // console
		// // because Future.get() waits for task to get completed
		// System.out.println("Thread "
		// + altitudeUpdate.get().getSourceId() + "  "
		// + new DateTime() + "::" + i + "-"
		// + altitudeUpdate.get());
		// } catch (InterruptedException | ExecutionException e) {
		// e.printStackTrace();
		// }
		// }
		// // shut down the executor service now
		// //executorService.shutdown();
		//
	}

}
