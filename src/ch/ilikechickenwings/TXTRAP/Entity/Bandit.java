package ch.ilikechickenwings.TXTRAP.Entity;

import ch.ilikechickenwings.TXTRAP.City;
import ch.ilikechickenwings.TXTRAP.Places.Place;

public class Bandit extends Human{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Bandit(float health, String name, City city, String responseLine1, Place place2) {
		super(health, name, city, responseLine1, place2);
		
	}

	@Override
	public String getResponseLine() {
		
		
		
		return null;
	}

}
