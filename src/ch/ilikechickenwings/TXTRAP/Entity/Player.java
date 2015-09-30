package ch.ilikechickenwings.TXTRAP.Entity;

import ch.ilikechickenwings.TXTRAP.City;
import ch.ilikechickenwings.TXTRAP.Places.Place;

public class Player extends Human{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String gameClass;
	
	
	public Player(String name, String gameClass, City city, Place place) {
		super(100.0f, name, city,"",place);
		setGameClass(gameClass);
	}


	/**
	 * @return the gameClass
	 */
	public String getGameClass() {
		return gameClass;
	}

	/**
	 * @param gameClass the gameClass to set
	 */
	public void setGameClass(String gameClass) {
		this.gameClass = gameClass;
	}


	@Override
	public String getResponseLine() {
			
		return getResponseLine();
	}


}
