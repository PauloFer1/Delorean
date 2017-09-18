package com.pfernand.datastore.identifier;

public interface IdentifierDAO {

	/*
	 * Create an identifier with one Observation (timestamp, data)
	 * 
	 * @param int id - id of the identifier
	 * @param int timestamp - timestamp of the observation
	 * @param String data - data of the observation
	 * @return String the data of the observation created
	 */
	public String create(int id, int timestamp, String data) throws IllegalArgumentException;
	
	/*
	 * Update an identifier inserting, or updating an observation with the given timestamp and data
	 * 
	 * @param int id - id of the identifier
	 * @param int timestamp - timestamp of the observation
	 * @param String data - data of the observation
	 * @return String the data of the prior observation as-of the timestamp given
	 */
	public String update(int id, int timestamp, String data) throws IllegalArgumentException;
	
	/*
	 * Delete all observation history of the given identifier
	 * 
	 * @param int id - id of the identifier
	 * @return String - The data of the observation with greatest timestamp that was deleted
	 */
	public String delete(int id) throws IllegalArgumentException;
	
	/*
	 * Delete all observation history of the given identifier  from the given timestamp forward
	 * 
	 * @param int id - id of the identifier
	 * @param int timestamp - timestamp from which to delete forward
	 * @return String - The data of the observation with latest timestamp
	 */
	public String delete(int id, int timestamp) throws IllegalArgumentException;
	
	/*
	 * Get the data from the observation for the given identifier as-of the given timestamp
	 * 
	 * @param int id - the id of the identifier
	 * @param int timestamp - the timestamp to get the data
	 * @return String - the data of the observation
	 */
	public String get(int id, int timestamp) throws IllegalArgumentException;
	
	/*
	 * Get the latest, as-of timestamp, Observation from the identifier
	 * 
	 * @param int id - the id of the identifier
	 * @return Observation - the observation object 
	 */
	public Observation latest(int id) throws IllegalArgumentException;
	
}
