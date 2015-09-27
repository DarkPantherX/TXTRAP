package ch.ilikechickenwings.TXTRAP;

import java.util.ArrayList;

import ch.ilikechickenwings.TXTRAP.Entity.Entity;
import ch.ilikechickenwings.TXTRAP.Frames.WorldFrame;

public class City {
	/**
	 * ArrayList with enitites in the city
	 */
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	
	private WorldFrame world;
	
	private String cityName;
	
	
	
	/**
	 *
	 * @param cityname Constructs the city with a new name
	 */
	public City(String cityname) {
		setCityName(cityname);
	}

	/**
	 * @return the world
	 */
	public WorldFrame getWorldFrame() {
		return world;
	}

	/**
	 * @param world the world to set
	 */
	public void setWorldFrame(WorldFrame world) {
		this.world = world;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	

}
