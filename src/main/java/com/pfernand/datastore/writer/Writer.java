package com.pfernand.datastore.writer;

public interface Writer {
	
	/*
	 * Receives a response as a String and deliver it to the destination
	 * 
	 *  @param String response - the response to be sent
	 */
	public void writeResponse(String response);
}
