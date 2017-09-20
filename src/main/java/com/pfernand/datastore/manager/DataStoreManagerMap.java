package com.pfernand.datastore.manager;


import com.pfernand.datastore.identifier.IdentifierDAO;
import com.pfernand.datastore.identifier.IdentifierHashmapImpl;
import com.pfernand.datastore.util.ParserUtil;

/*
 * Implementation of DataManager which uses IdentifierHashmapImpl
 */

public class DataStoreManagerMap implements DataStoreManager {
	
	private IdentifierDAO m_Identifier = new IdentifierHashmapImpl();

	public String processLine(String line) throws IllegalArgumentException {
	
		// Validate and get request args
		String[] args = ParserUtil.getCommandsFromString(line);
		
		if(args.length == 0) {
			throw new IllegalArgumentException("Illegal input command, please provide a valid command.");
		}
		
		// Get the action to perform
		Actions action;
		try {
			action = Actions.valueOf(args[0]);
		} catch (Exception e) {
			throw new IllegalArgumentException("Illegal input command, please provide a valid command.");
		}

		// Argument validations
		// Arguments shouldn't be higher than 4
		if(args.length > 4) {
			throw new IllegalArgumentException("Number of arguments are higher than expected.");
		}
		// Arguments shouldn't be lower than 4, except for DELETE, GET AND LATEST (2, 3 and 2)
		if( (action != Actions.DELETE && action != Actions.GET && action != Actions.LATEST && args.length < 4) || 
				((action == Actions.DELETE || action == Actions.LATEST ) && args.length < 2) ||
				(action == Actions.GET && args.length < 3) ) {
			throw new IllegalArgumentException("Number of arguments are lower than expected.");
		}
		
		String result = "";
		switch (action) {
		case CREATE:
			result = m_Identifier.create(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
			break;
		case UPDATE:
			result = m_Identifier.update(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
			break;
		case DELETE:
			if(args.length < 3) {
				result = m_Identifier.delete(Integer.parseInt(args[1]));
			} else {
				result = m_Identifier.delete(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			}
			break;
		case GET:
			result = m_Identifier.get(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			break;
		case LATEST:
			result = m_Identifier.latest(Integer.parseInt(args[1])).toString();
			break;
		default:
			throw new IllegalArgumentException("Illegal input command, please provide a valid command.");
		}
		
		return result;
	}
	
	/*
	 * Get the identifier (testing purpose)
	 */
	public IdentifierDAO getIdentifier() {
		return m_Identifier;
	}

}
