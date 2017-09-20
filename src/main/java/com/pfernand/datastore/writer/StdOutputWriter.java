package com.pfernand.datastore.writer;

/*
 * Implementation of Writer using System.out
 */

public class StdOutputWriter implements Writer {

	public void writeResponse(String response) {
		System.out.println(response);
	}

}
