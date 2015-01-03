package net.rockshore.prototype.threading.core;

import org.joda.time.DateTime;

/**
 * Represents a single message that reports a new altitude for a flight.
 * 
 * @author neil dunlop
 *
 */
public class FlightAltitudeUpdate {

	protected String sourceId;
	protected DateTime eventTimestamp;
	protected String icao;
	protected int altitude;
	

	public String getSourceId() {
		return sourceId;
	}
	
	public DateTime getEventTimestamp() {
		return eventTimestamp;
	}

	public String getIcao() {
		return icao;
	}

	public int getAltitude() {
		return altitude;
	}

	

	public FlightAltitudeUpdate(String sourceId, DateTime eventTimestamp,
			String icao, int altitude) {
		super();
		this.sourceId = sourceId;
		this.eventTimestamp = eventTimestamp;
		this.icao = icao;
		this.altitude = altitude;
	}

}
