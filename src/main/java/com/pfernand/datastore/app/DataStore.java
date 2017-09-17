package com.pfernand.datastore.app;

import java.io.IOException;

import com.pfernand.datastore.manager.DataStoreManager;
import com.pfernand.datastore.manager.DataStoreManagerMap;
import com.pfernand.datastore.reader.Reader;
import com.pfernand.datastore.reader.StdInputReader;
import com.pfernand.datastore.writer.StdOutputWriter;
import com.pfernand.datastore.writer.Writer;

public class DataStore {

	private static DataStoreManager m_DataStoreManager = new DataStoreManagerMap();
	private static Reader m_Reader = new StdInputReader();
	private static Writer m_Writer = new StdOutputWriter();
	
	public static void main( String[] args )
	{	 
		 String inputCommand = "";
		 
		try {
			
			// Infinite cycle to read input
			while(true) {
				inputCommand = m_Reader.getCommand();
				
				// QUIT if command found
				if(inputCommand.equals("QUIT")) {
					return;
				}
				
				String result = "";
				try {
					// Process input request
					result = m_DataStoreManager.processLine(inputCommand);
					
					// Write response
					m_Writer.writeResponse("OK " + result);
				} catch (Exception e) {
					m_Writer.writeResponse( "ERR " + e.getMessage() );
				}
			}
		} catch (IOException e) {
			m_Writer.writeResponse( "ERR " + e.getMessage() );
		} catch (Exception e) {
			m_Writer.writeResponse( "ERR Unespected error: " + e.getMessage() );
		}
	}
}
