package ch.ilikechickenwings.TXTRAP.Entity;

import ch.ilikechickenwings.TXTRAP.City;

public class Human extends Entity{

	/** Saves the city the human lives in */
	private City city;
	
	/** Has the name of the human */
	private String name;
	
	public Human(float health, String name, City city){
		super(health);
		setName(name);
		setCity(city);
		
	}
	
	
	
	
	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}




	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}




	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
