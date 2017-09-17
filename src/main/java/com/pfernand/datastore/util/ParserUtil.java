package com.pfernand.datastore.util;

public class ParserUtil {

	public static String[] getCommandsFromString(String line) {
		
		return line.split("\\s+");
	}
}
