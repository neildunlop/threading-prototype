package net.rockshore.prototype.threading.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Demonstration application that uses a number of threads to create random
 * flight update messages that are placed onto a blocking queue.
 * 
 * These messages are read off the queue by another set of associator threads
 * that share a flight list to determine if a flight should be created or simply
 * updated.
 * 
 * The final stage simply updates the flight domain with the new information. It
 * either creates the flight or updates its properties.
 * 
 * @author neildunlop
 *
 */
public class App2 {

	// TODO: Check what are good sizes/settings for these collections
	// (ArrayBlockingQueue size is just a guess for now!)
	private static ConcurrentMap<String, Long> flightList = new ConcurrentHashMap<String, Long>();
	private static BlockingQueue<FlightAltitudeUpdate> associatorInboundQueue = new ArrayBlockingQueue<FlightAltitudeUpdate>(
			500);
	private static BlockingQueue<FlightAltitudeUpdate> associatorOutboundQueue = new ArrayBlockingQueue<FlightAltitudeUpdate>(
			500);

	public static void main(String[] args) {

		AltitudeUpdateDataGeneratorService dataGeneratorService = new AltitudeUpdateDataGeneratorService(
				4, 50, associatorInboundQueue);
		dataGeneratorService.start();

		FlightMessageAssociationService associationService = new FlightMessageAssociationService(
				2, associatorInboundQueue, associatorOutboundQueue);
		associationService.start();

	}

}
