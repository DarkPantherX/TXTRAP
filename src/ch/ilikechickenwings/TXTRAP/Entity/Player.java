package ch.ilikechickenwings.TXTRAP.Entity;

import ch.ilikechickenwings.TXTRAP.Frames.WorldFrame;
import ch.ilikechickenwings.TXTRAP.City;;

public class Player extends Human{
	

	private String gameClass;

	public Player(String name, String gameClass) {
		super(100.0f, name, (City)WorldFrame.cities.get(0));
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

}
