package com.pfernand.datastore.writer;

public class StdOutputWriter implements Writer {

	public void writeResponse(String response) {
		System.out.println(response);
	}

}
