package com.pfernand.datastore.identifier;

public class Observation implements Comparable<Observation> {

	private int timestamp;
	private String data;
	
	/*
	 * Getters and Setters
	 */
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	public int getTimestamp() {
		return timestamp;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	public String getData() {
		return data;
	}
	
	/*
	 * Constructors
	 */
	public Observation(){
		
	}
	public Observation(int timestamp, String data){
		this.timestamp = timestamp;
		this.data = data;
	}
	
	/*
	 * Methods
	 */
	
	@Override
	public int compareTo(Observation o) {
		
		if(timestamp < o.timestamp) {
			return 1;
		} else if (timestamp > o.timestamp) {
			return -1;
		}
		
		return 0;
	}
	
	@Override
	public String toString() {
		return timestamp + " " + data;
	}
	
}
