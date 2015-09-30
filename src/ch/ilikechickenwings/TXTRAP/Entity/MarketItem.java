package ch.ilikechickenwings.TXTRAP.Entity;

public class MarketItem extends Item{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int price;
	
	
	public MarketItem(String name, int price1){
		super(name,0);
		setPrice(price1);
		
	}


	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}


	/**
	 * @param price1 the price to set
	 */
	public void setPrice(int price1) {
		this.price = price1;
	}
	
	
	
}
