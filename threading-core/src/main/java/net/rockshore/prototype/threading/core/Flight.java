package net.rockshore.prototype.threading.core;

import org.joda.time.DateTime;

/**
 * Simple representation of the most update date information about a flight.
 * 
 * @author neildunlop
 *
 */
public class Flight {

	protected String icaoCode;
	protected int altitude;
	protected String lastUpdateSourceId;
	protected DateTime lastUpdateTimestamp;
	
	public String getIcaoCode() {
		return icaoCode;
	}
	public int getAltitude() {
		return altitude;
	}
	public String getLastUpdateSourceId() {
		return lastUpdateSourceId;
	}
	public DateTime getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}
	public Flight(String icaoCode, int altitude, String lastUpdateSourceId,
			DateTime lastUpdateTimestamp) {
		super();
		this.icaoCode = icaoCode;
		this.altitude = altitude;
		this.lastUpdateSourceId = lastUpdateSourceId;
		this.lastUpdateTimestamp = lastUpdateTimestamp;
	}
	
	//hashcode and equals are needed for anything that goes into
	//a concurrent hash map.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + altitude;
		result = prime * result
				+ ((icaoCode == null) ? 0 : icaoCode.hashCode());
		result = prime
				* result
				+ ((lastUpdateSourceId == null) ? 0 : lastUpdateSourceId
						.hashCode());
		result = prime
				* result
				+ ((lastUpdateTimestamp == null) ? 0 : lastUpdateTimestamp
						.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (altitude != other.altitude)
			return false;
		if (icaoCode == null) {
			if (other.icaoCode != null)
				return false;
		} else if (!icaoCode.equals(other.icaoCode))
			return false;
		if (lastUpdateSourceId == null) {
			if (other.lastUpdateSourceId != null)
				return false;
		} else if (!lastUpdateSourceId.equals(other.lastUpdateSourceId))
			return false;
		if (lastUpdateTimestamp == null) {
			if (other.lastUpdateTimestamp != null)
				return false;
		} else if (!lastUpdateTimestamp.equals(other.lastUpdateTimestamp))
			return false;
		return true;
	}
	

	
	
}
