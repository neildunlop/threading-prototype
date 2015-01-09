package net.rockshore.prototype.threading.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.joda.time.DateTime;

/**
 * Simple service to associate a message with a flight in our domain. If the
 * flight has not been seen before, it is added to the list of flights in the
 * domain (in production association is configurable and follows potentially
 * complex rules that may take time. Basically, we want to associate a message
 * with a unique FlightId if we have one. If we dont have one, we want to create
 * one and use that from that point on.
 * 
 * @author neildunlop
 *
 */
public class FlightMessageAssociator implements Callable {

	private static ConcurrentMap<String, Long> flightList;
	private BlockingQueue<FlightAltitudeUpdate> inboundQueue;
	private BlockingQueue<FlightAltitudeUpdate> outboundQueue;

	private final static Logger LOGGER = Logger
			.getLogger(FlightMessageAssociator.class.getName());

	public FlightMessageAssociator(ConcurrentMap<String, Long> flightList,
			BlockingQueue<FlightAltitudeUpdate> inboundQueue,
			BlockingQueue<FlightAltitudeUpdate> outboundQueue) {
		super();
		this.inboundQueue = inboundQueue;
		this.outboundQueue = outboundQueue;
		FlightMessageAssociator.flightList = flightList;
	}

	@Override
	public Object call() throws Exception {

		if (Thread.currentThread().isInterrupted()) {
			// Cannot use InterruptedException since it's checked
			throw new RuntimeException();
		}

		long currentTime = System.currentTimeMillis();

		// get a message from our inbound queue
		FlightAltitudeUpdate message = inboundQueue.take();

		LOGGER.setLevel(Level.INFO);
	    LOGGER.info("Doing association on Flight '" + message.getIcao()
				+ "' at " + new DateTime(currentTime).toString());

	    // comment in to simulate flight association being slow in production.
		// Thread.sleep(100);

		currentTime = System.currentTimeMillis();

		// if we've seen the flight before then send an update message.
		// if we've not seen the flight before then send a create message.
		// (These would be axon commands in production and we would block the
		// sending
		// of the event to CEP until we get a response from the Future we send
		// to Axon).
		Long putResult = FlightMessageAssociator.flightList.putIfAbsent(
				message.getIcao(), currentTime);
		if (putResult == null) {
			LOGGER.info("Creating Flight '" + message.getIcao()
					+ "' at " + new DateTime(currentTime).toString());
		} else {
			LOGGER.info("Updating Flight '" + message.getIcao()
					+ "' at " + new DateTime(currentTime).toString());
		}

		// TODO: Comment back in.. removed just to see if we can get a full flow
		// of messages

		// outboundQueue.put(message);
		return null;
	}

}
