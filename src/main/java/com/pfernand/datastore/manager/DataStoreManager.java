package com.pfernand.datastore.manager;

public interface DataStoreManager {

	// Input Actions
	public static enum Actions {CREATE, UPDATE, DELETE, GET, LATEST};
	
	/*
	 * Process the input line and choose which operation to use
	 * 
	 * @Param String line - the input line to process
	 */
	public String processLine(String line) throws IllegalArgumentException;
}
