package com.pfernand.datastore.identifier;

import java.util.Iterator;
import java.util.TreeSet;

public class IdentifierTreeSet extends Identifier {

	// Treeset to store Observation order by latest timestamp
	private TreeSet<Observation> observations = new TreeSet<Observation>();
	
	/*
	 * Getters and Setters
	 */
	public TreeSet<Observation> getObservations() {
		return observations;
	}
	public void setObservations(TreeSet<Observation> observations) {
		this.observations = observations;
	} 
	
	/*
	 * Constructors
	 */
	public IdentifierTreeSet() {
		
	}
	public IdentifierTreeSet(int id) {
		super(id);
	}
	
	/*
	 * Methods
	 */
	
	/*
	 * Insert an Obbservation into the observations data structure
	 * 
	 * @param int timestamp - the timestamp of the observations
	 * @param String data - the data of the observation
	 */
	public void insertObservation(int timestamp, String data) throws IllegalArgumentException {
		
		// Check if exists 
		if(observations.contains(new Observation(timestamp ,data))) {
			
			// Find the observation to update it
			Iterator<Observation> it = observations.iterator();  
			while(it.hasNext()){  
				Observation tmpObs = it.next();
				if(timestamp == tmpObs.getTimestamp()) {
					tmpObs.setData(data);
					break;
				}
			}	
		} else {
			// observation doesn't exists, add it
			observations.add(new Observation(timestamp, data));
			
		}
	}
	
	/*
	 * Return the observation with the latest timestamp (first element from the observations set)
	 *
	 * @return Observation - the observation with latest timestamp
	 */
	public Observation getLatest() throws IllegalArgumentException {
		
		// Check if the set is empty, throw exception if so
		if(observations.isEmpty()) {
			throw new IllegalArgumentException("History of Observations is empty.");
		}
		
		return observations.first();
	}
	
	/*
	 * Get the data from the prior observation as-of that timestamp.
	 * 
	 * @param int timestamp - the timestamp to compare
	 */
	public Observation getClosestObservationWithLowerEqualTimestamp(int timestamp) throws IllegalArgumentException {
		
		// Check if the set is empty, throw exception if so
		if(observations.isEmpty()) {
			throw new IllegalArgumentException("History of Observations is empty.");
		}
		
		// Iterate the set to find the first observation with lower timestamp than the param
		Iterator<Observation> it = observations.iterator();  
		while(it.hasNext()){  
			Observation tmpObs = it.next();
			if(timestamp >= tmpObs.getTimestamp()) {
				return tmpObs;
			}
		}	
		 
		// Object not found, throw Exception
		throw new IllegalArgumentException("No timestamp lower or equal than " + timestamp);
	}
	
	/*
	 * Delete All history from identifier
	 */
	public void deleteAllHistory() {
		// Clear the observations set
		observations.clear();
	}
	
	/*
	 * Delete observations from the given timestamp forward
	 * 
	 * @param int timestamp - the timestamp to delete from
	 */
	public void deleteObservationsFromTimestampForward(int timestamp) throws IllegalArgumentException {
		
		if(timestamp > observations.first().getTimestamp()) {
			throw new IllegalArgumentException("No available observations to delete.");
		}
		
		// Iterate the set while given timestamp is lower or equal and delete higher ones
		Iterator<Observation> it = observations.iterator(); 	
		while(it.hasNext()){  
			Observation tmpObs = it.next();
			if(timestamp <= tmpObs.getTimestamp()) {
				it.remove();
			} else {
				break;
			}
		}	
	}
		
}
