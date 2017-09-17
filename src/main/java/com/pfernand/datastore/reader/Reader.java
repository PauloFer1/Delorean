package com.pfernand.datastore.reader;

import java.io.IOException;

public interface Reader {

	public String getCommand() throws IOException;
}
