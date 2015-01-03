package net.rockshore.prototype.threading.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.joda.time.DateTime;

public class AltitudeMessageProcessingService implements
		FlightMessageProcessingService<FlightAltitudeUpdate> {

	// list of flights we have seen (ie, whether we should create or update)
	private static final ConcurrentMap<String, Long> flightList = new ConcurrentHashMap<String, Long>();

	// this represents the domain - up to date record for each flight.
	private static final ConcurrentMap<String, Flight> flightDomain = new ConcurrentHashMap<String, Flight>();

	@Override
	public void associate(FlightAltitudeUpdate message) {

		// FlightMessageAssociationService... to make use of
		// FlightMessageAssociators? multithreaded?

	}

	@Override
	public void prioritise(FlightAltitudeUpdate message) {

		// doing nothing for now...
	}

	@Override
	public void updateDomain(FlightAltitudeUpdate message) {

		long currentTime = System.currentTimeMillis();
		Flight flight = new Flight(message.getIcao(), message.getAltitude(),
				message.getSourceId(), new DateTime(currentTime));
		AltitudeMessageProcessingService.flightDomain.putIfAbsent(
				message.getIcao(), flight);
	}

}
