package net.rockshore.prototype.threading.core;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fluttercode.datafactory.impl.DataFactory;
import org.joda.time.DateTime;

/**
 * Helper class to generate random altitude update messages.
 * 
 * @author neildunlop
 *
 */
public class AltitudeUpdateDataGenerator implements Callable<Object> {

	private DataFactory dataFactory = new DataFactory();
	// private String[] dataSourceNames = { "Radar1", "Radar2" };
	private String[] flightIcaos = { "BAW123", "BAW222", "LFT111", "LFT999",
			"UAE666", "UAE226" };

	private BlockingQueue<FlightAltitudeUpdate> outputQueue;
	private final static Logger LOGGER = Logger
			.getLogger(FlightMessageAssociator.class.getName());

	public AltitudeUpdateDataGenerator(
			BlockingQueue<FlightAltitudeUpdate> outputQueue) {
		super();
		this.outputQueue = outputQueue;
	}

	private FlightAltitudeUpdate generateAltitudeUpdate() {
		// String sourceId = dataFactory.getItem(dataSourceNames);
		String sourceId = Objects.toString(Thread.currentThread().getId());
		DateTime timestamp = new DateTime();
		String icao = dataFactory.getItem(flightIcaos);
		int altitude = dataFactory.getNumberBetween(200, 20000);

		FlightAltitudeUpdate altUpdate = new FlightAltitudeUpdate(sourceId,
				timestamp, icao, altitude);
		return altUpdate;
	}

	public Object call() throws InterruptedException {

		LOGGER.setLevel(Level.INFO);
		if (Thread.currentThread().isInterrupted()) {
			// Cannot use InterruptedException since it's checked
			throw new RuntimeException();
		}

		FlightAltitudeUpdate altUpdate = generateAltitudeUpdate();

		LOGGER.info("Adding to source Queue from Thread::"
				+ altUpdate.getSourceId() + "  " + new DateTime());

		this.outputQueue.put(altUpdate);
		LOGGER.info("Remaining Capacity:"
				+ this.outputQueue.remainingCapacity());

		return true;
	}

}
