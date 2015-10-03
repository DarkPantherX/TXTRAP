package ch.ilikechickenwings.TXTRAP.Places;

import java.util.ArrayList;

import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.Entity.Gold;
import ch.ilikechickenwings.TXTRAP.Entity.Item;
import ch.ilikechickenwings.TXTRAP.Entity.MarketItem;
import ch.ilikechickenwings.TXTRAP.Entity.Player;
import ch.ilikechickenwings.TXTRAP.Frames.WorldFrame;

public class Market extends Place{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<MarketItem> items = new ArrayList<MarketItem>();
	
	
	public Market(WorldFrame wF){
		setName("Market");
		setWorldFrame(wF);
		items.add(new MarketItem("Bananas",10));
	
	}
	
	
	@Override
	public void processInput(String[] s) {
		
		switch (s[0].toLowerCase()){
			case "help":
				Console.log("Available commands:"
						+"\n showitems -> shows the items to sell"
						+"\n buy <item name> <optinal: quantity> -> buys the item"
						+"\n sell <item name> <optional: quantity> -> sells the item"
						+"\n leave -> leave the market");
				break;
			case "showitems":
				Console.log("On the market available is: ", Console.standartOutput);
				MarketItem it;
				System.out.println(items.size());
				if(items.size()>0){
					for(int i2=0;i2<items.size();i2++){
					it = (MarketItem)items.get(i2);
					Console.log("-> "+it.getName()+"\t"+it.getPrice(), Console.standartListOutput);
					}
				}else{
					Console.log("-->nothing<-- (market is poor as fuck)", Console.standartListOutput);
				}
				break;
			case "buy":
				MarketItem item=null;
				for(int i=0;i<items.size();i++){
					if(s[1].toLowerCase().equals(items.get(i).getName().toLowerCase()))
						item=items.get(i);
				}
				if(item==null){ // item does not exist in this shop
					Console.log("You cannot buy "+s[1]+" here.", Console.standartOutput);
				}else{ // item exists
					Gold gold=new Gold(0);
					ArrayList<Item> inventory = getWorldFrame().getPlayer().getInventory();
					for(int i=0;i<inventory.size();i++){
						if(inventory.get(i) instanceof Gold)
							gold=(Gold) inventory.get(i);
					}
					
					item.setQuantity(1);
					if(s.length>2)
						item.setQuantity(Integer.parseInt(s[2]));
					
					if(item.getPrice()*item.getQuantity()>gold.getQuantity()){ // too expensive
						Console.log(item.getQuantity()+" "+s[1]+" is too expensive.", Console.standartOutput);
					}else{ // buy it
						gold.setQuantity(gold.getQuantity()-item.getPrice()*item.getQuantity());
						inventory.add(item);
						Console.log("You bought "+item.getQuantity()+" "+item.getName()+" for "+item.getPrice()+" gold each."
									+"\n You have "+gold.getQuantity()+" gold left.", Console.standartOutput);
					}
				} // end check if item exists
				
				break;
			case "sell":
				break;
			case "leave":
			case "stop":
				stopInteract(null);
				Console.clearlog();
				getWorldFrame().getPlayer().setPlace(null);
				Console.logSingleLine("You left the market",Console.standartEvent);
				break;
			default:
				Console.log("Command not found",Console.errorOutput);
				break;
		
		
		
		
		}
	}

	@Override
	public void interact(Player player) {
		getWorldFrame().getMainFrame().setProcessable(this);
		Console.clearlog();
		Console.logSingleLine("Welcome to the market, write 'help' for more information");
		
		
	}


	@Override
	public void stopInteract(Player player) {
		getWorldFrame().getMainFrame().setProcessable(getWorldFrame());
		
	}

}
