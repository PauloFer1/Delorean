package com.pfernand.datastore.reader;

import java.io.IOException;

public interface Reader {

	/*
	 * Reads a Command and return it as a String
	 * 
	 * @return String - the command read
	 */
	public String getCommand() throws IOException;
}
