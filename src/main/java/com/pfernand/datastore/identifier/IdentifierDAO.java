package com.pfernand.datastore.identifier;

public interface IdentifierDAO {

	public String create(int id, int timestamp, String data) throws IllegalArgumentException;
	
	public String update(int id, int timestamp, String data) throws IllegalArgumentException;
	
	public String delete(int id) throws IllegalArgumentException;
	
	public String delete(int id, int timestamp) throws IllegalArgumentException;
	
	public String get(int id, int timestamp) throws IllegalArgumentException;
	
	public Observation latest(int id) throws IllegalArgumentException;
	
}
