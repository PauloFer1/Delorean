package com.pfernand.datastore.identifier;

public class Identifier {

	private int id;
	
	/*
	 * Getters and Setters
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/*
	 * Constructors
	 */
	public Identifier() {
		
	}
	public Identifier(int id) {
		this.id = id;
	}
}
