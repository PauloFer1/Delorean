package com.pfernand.datastore.reader;

import java.io.IOException;
import java.util.Scanner;

/*
 * Implementation of Reader using System.in
 */

public class StdInputReader implements Reader {
	
	Scanner m_Scanner = new Scanner(System.in);
	
	public String getCommand() throws IOException{
		
		String command = "";
		
		command = m_Scanner.nextLine();
		
		if(command == "") {
			throw new IOException("Illegal Input, please provide a valid input.");
		}
			
		return command;
	}

}
