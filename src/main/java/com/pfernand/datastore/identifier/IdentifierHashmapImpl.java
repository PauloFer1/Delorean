package com.pfernand.datastore.identifier;

import java.util.HashMap;

/*
 * Implementation of IdentifierDAO using an HashMap to store
 * the data into memory. 
 */
public class IdentifierHashmapImpl implements IdentifierDAO {

	private HashMap<Integer, IdentifierTreeSet> m_Storer = new HashMap<Integer, IdentifierTreeSet>();
	
	@Override
	public String create(int id, int timestamp, String data) throws IllegalArgumentException {
		
		// verify if id exists
		if(m_Storer.containsKey(id)) {
			throw new IllegalArgumentException("Identifier with id: " + id + " already exists.");
		}
		
		// create new identifier
		IdentifierTreeSet identifier = new IdentifierTreeSet(id);
		identifier.insertObservation(timestamp, data);
		
		// Store it into the hashmap
		m_Storer.put(id, identifier);
		
		// Everything's OK, return the data
		return data;
	}

	@Override
	public String update(int id, int timestamp, String data) throws IllegalArgumentException {

		// verify if id exists
		if(!m_Storer.containsKey(id)) {
			throw new IllegalArgumentException("Identifier with id: " + id + " do not exist.");
		} 
		
		// Get identifier
		IdentifierTreeSet identifier = m_Storer.get(id);
		
		/* Get the data from the prior observation as-of that timestamp.
		 * Assuming that we can insert an older timestamp, we need to search
		 * the closest one and don't assume that is the latest one.
		 */
		String closestLowerEqualData = "";
		try {
			closestLowerEqualData = identifier.getClosestObservationWithLowerEqualTimestamp(timestamp).getData();
		} catch (IllegalArgumentException e) {
			// There is no timestamp lower than the provided, set result to the same
			closestLowerEqualData = data;
		}
				
		identifier.insertObservation(timestamp, data);
		
		return closestLowerEqualData;
	}

	@Override
	public String delete(int id) throws IllegalArgumentException { 
		
		// Get data with greatest timestamp. Id validation is in the latest(id) method
		String dataWithGreatestTimestamp = latest(id).getData();
		
		// Get identifier
		IdentifierTreeSet identifier = m_Storer.get(id);
		identifier.deleteAllHistory();
		
		return dataWithGreatestTimestamp;
	}

	@Override
	public String delete(int id, int timestamp) throws IllegalArgumentException {
		
		// verify if id exists
		if(!m_Storer.containsKey(id)) {
			throw new IllegalArgumentException("Identifier with id: " + id + " do not exist.");
		} 
		
		// Get identifier
		IdentifierTreeSet identifier = m_Storer.get(id);
		identifier.deleteObservationsFromTimestampForward(timestamp);
				
		// Return the latest data after delete 
		return identifier.getLatest().getData();
	}

	@Override
	public String get(int id, int timestamp) throws IllegalArgumentException {
		
		// verify if id exists
		if(!m_Storer.containsKey(id)) {
			throw new IllegalArgumentException("Identifier with id: " + id + " do not exist.");
		} 
						
		// Get identifier
		IdentifierTreeSet identifier = m_Storer.get(id);
		
		// Get data from the observation with the same or closest lower timestamp
		String closestLowerEqualData = identifier.getClosestObservationWithLowerEqualTimestamp(timestamp).getData();
		
		return closestLowerEqualData;
	}

	@Override
	public Observation latest(int id) throws IllegalArgumentException {
		
		// verify if id exists
		if(!m_Storer.containsKey(id)) {
			throw new IllegalArgumentException("Identifier with id: " + id + " do not exist.");
		}
		
		// Get Identifier
		IdentifierTreeSet identifier = m_Storer.get(id);
		
		return identifier.getLatest();
	}
	
	/*
	 * Get the size of the observation history by given id (testing purposes)
	 * 
	 * @param int id - id of the identifier
	 */
	public int getHistorySizeById(int id) throws IllegalArgumentException {
		// verify if id exists
		if(!m_Storer.containsKey(id)) {
			throw new IllegalArgumentException("Identifier with id: " + id + " do not exist.");
		}
		
		// Get Identifier
		IdentifierTreeSet identifier = m_Storer.get(id);
		
		return identifier.getObservations().size();
	}
}
