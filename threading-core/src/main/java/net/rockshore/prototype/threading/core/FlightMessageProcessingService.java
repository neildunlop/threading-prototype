package net.rockshore.prototype.threading.core;

/**
 * Defines the operations exposed implementations of the Flight Message
 * Processing Service that coordinates the operations that happen to flight data
 * as it arrives from data sources and is used to update the details of the
 * current flight list. (Basically, its a pipeline containing a number of
 * operations that act upon the arriving messages to decide if the data should
 * be applied to our master flight list).
 * 
 * @author neildunlop
 *
 * @param <T>
 */
public interface FlightMessageProcessingService<T> {

	public void associate(T message);

	public void prioritise(T message);

	public void updateDomain(T message);
}
