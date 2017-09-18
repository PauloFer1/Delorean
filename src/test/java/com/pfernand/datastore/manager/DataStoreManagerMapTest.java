package com.pfernand.datastore.manager;

import com.pfernand.datastore.identifier.IdentifierDAO;
import com.pfernand.datastore.identifier.IdentifierHashmapImpl;
import com.pfernand.datastore.identifier.Observation;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/*
 * Basic Unit testing
 */
public class DataStoreManagerMapTest extends TestCase {
	
	public DataStoreManagerMapTest(String testName) {
		super(testName);
	}
	
	public static Test suite() {
		System.out.println("::::::::: DataStoreManagerMapTest");
		
		return new TestSuite(DataStoreManagerMapTest.class);
	}
	
	/*
	 * Test processing a valid create command 
	 */
	@org.junit.Test
	public void testProcessLineValidCreateCommand(){
		System.out.println("::Running testProcessLineValidCreateCommand");
		
		DataStoreManagerMap dataStoreMan = new DataStoreManagerMap();
		IdentifierDAO identifier = dataStoreMan.getIdentifier();
		
		String result = dataStoreMan.processLine("CREATE 1 120 1.5");
		String idData = identifier.get(1, 120);
		
		System.out.println("::Assert Output");
		assertEquals("1.5", result);
		System.out.println("::Assert Create data");
		assertEquals("1.5", idData);
	}
	
	/*
	 * Test processing a valid update command
	 */
	@org.junit.Test
	public void testProcessLineValidUpdateCommand() {
		System.out.println("::Running testProcessLineValidUpdateCommand");
		
		DataStoreManagerMap dataStoreMan = new DataStoreManagerMap();
		IdentifierDAO identifier = dataStoreMan.getIdentifier();
		
		dataStoreMan.processLine("CREATE 1 120 1.5");
		String result = dataStoreMan.processLine("UPDATE 1 125 2.5");
		String idData = identifier.get(1, 125);
		
		System.out.println("::Assert Output");
		assertEquals("1.5", result);
		System.out.println("::Assert Update data");
		assertEquals("2.5", idData);
	}
	
	/*
	 * Test processing a valid update overriding an Observation
	 */
	@org.junit.Test
	public void testProcessLineValidUpdateOverridingObservationCommand() {
		System.out.println("::Running testProcessLineValidUpdateOverridingObservationCommand");
		
		DataStoreManagerMap dataStoreMan = new DataStoreManagerMap();
		IdentifierDAO identifier = dataStoreMan.getIdentifier();
		
		dataStoreMan.processLine("CREATE 1 120 1.5");
		String result = dataStoreMan.processLine("UPDATE 1 120 2.5");
		String idData = identifier.get(1, 120);
		
		System.out.println("::Assert Output");
		assertEquals("1.5", result);
		System.out.println("::Assert Update data");
		assertEquals("2.5", idData);
	}
	
	/*
	 * Test processing a valid get command
	 */
	@org.junit.Test
	public void testProcessLineValidGetCommand() {
		System.out.println("::Running testProcessLineValidGetCommand");
		
		DataStoreManagerMap dataStoreMan = new DataStoreManagerMap();
		IdentifierDAO identifier = dataStoreMan.getIdentifier();
		
		dataStoreMan.processLine("CREATE 1 120 1.5");
		dataStoreMan.processLine("UPDATE 1 125 2.5");
		String idData = identifier.get(1, 122);

		System.out.println("::Assert Output");
		assertEquals("1.5", idData);
	}
	
	/*
	 * Test processing a valid latest command
	 */
	@org.junit.Test
	public void testProcessLineValidLatestCommand() {
		System.out.println("::Running testProcessLineValidLatestCommand");
		
		DataStoreManagerMap dataStoreMan = new DataStoreManagerMap();
		IdentifierDAO identifier = dataStoreMan.getIdentifier();
		
		dataStoreMan.processLine("CREATE 1 120 1.5");
		dataStoreMan.processLine("UPDATE 1 125 2.5");
		Observation latest = identifier.latest(1);

		System.out.println("::Assert Correct data");
		assertEquals("2.5", latest.getData());
		System.out.println("::Assert Correct timestamp");
		assertEquals(125, latest.getTimestamp());
	}
	
	/*
	 * Test processing a valid delete command with no timestamp
	 */
	@org.junit.Test
	public void testProcessLineValidDeleteCommandNoTimestamp() {
		System.out.println("::Running testProcessLineValidDeleteCommandNoTimestamp");
		
		DataStoreManagerMap dataStoreMan = new DataStoreManagerMap();
		IdentifierDAO identifier = dataStoreMan.getIdentifier();
		
		dataStoreMan.processLine("CREATE 1 120 1.5");
		dataStoreMan.processLine("UPDATE 1 125 2.5");
		String result = dataStoreMan.processLine("DELETE 1");
		int size = ((IdentifierHashmapImpl) identifier).getHistorySizeById(1);
		
		System.out.println("::Assert Output");
		assertEquals("2.5", result);
		System.out.println("::Assert Empty History");
		assertEquals(0, size);
	}
	
	/*
	 * Test processing a valid delete command
	 */
	@org.junit.Test
	public void testProcessLineValidDeleteCommand() {
		System.out.println("::Running testProcessLineValidDeleteCommand");
		
		DataStoreManagerMap dataStoreMan = new DataStoreManagerMap();
		IdentifierDAO identifier = dataStoreMan.getIdentifier();
		
		dataStoreMan.processLine("CREATE 1 120 1.5");
		dataStoreMan.processLine("UPDATE 1 125 2.5");
		String result = dataStoreMan.processLine("DELETE 1 122");
		Observation latest = identifier.latest(1);

		System.out.println("::Assert Output");
		assertEquals("1.5", result);
		System.out.println("::Assert the latest was deleted");
		assertEquals("1.5", latest.getData());
		assertEquals(120, latest.getTimestamp());
	}

}
