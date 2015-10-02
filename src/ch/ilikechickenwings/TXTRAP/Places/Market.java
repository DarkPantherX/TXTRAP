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

	private ArrayList<Item> items = new ArrayList<Item>();
	
	
	public Market(WorldFrame wF){
		setName("Market");
		setWorldFrame(wF);
		
		
	}
	
	
	@Override
	public void processInput(String[] s) {
		
		switch (s[0].toLowerCase()){
			case "help":
				Console.log("Available commands:"
						+"\n showitems -> shows the items to sell"
						+"\n buy <item name> <optinal: quantity> -> buys the item"
						+"\n sell <item name> <optional: quantity> -> shows the items to sell"
						+"\n stop -> Stops the interaction");
				break;
			case "showitems":
				Console.log("On the market available is: ", Console.standartOutput);
				MarketItem it;
				if(items.size()>0){
					for(int i2=0;i2<items.size();i2++){
					it = (MarketItem) items.get(i2);
					Console.log("->"+it.getName(), Console.standartListOutput);
					}
				}else{
					Console.log("-->nothing<-- (market is poor as fuck)", Console.standartListOutput);
				}
				break;
			case "buy":
				boolean done=false;
				if(items.size()>0){
					for(int i2=0;i2<items.size();i2++){
					it =  (MarketItem)items.get(i2);
					if(s[1].toLowerCase().equals(it.getName().toLowerCase())){
						for(int i3=0;i3<getWorldFrame().getPlayer().getInventory().size();i3++){
							Item im=(Item)getWorldFrame().getPlayer().getInventory().get(i3);
							if(im instanceof Gold){
								if(im.getQuantity()>=Integer.parseInt(s[2])*it.getPrice()){
									im.setQuantity(im.getQuantity()-Integer.parseInt(s[2])*it.getPrice());
									
									for(int i4=0;i4<getWorldFrame().getPlayer().getInventory().size();i4++){
										Item i1= getWorldFrame().getPlayer().getInventory().get(i3);
										if(i1.getName().equals(it.getName())){
											i1.setQuantity(i1.getQuantity()+Integer.parseInt(s[2]));
										}else{
											getWorldFrame().getPlayer().getInventory().add(new Item(it.getName(),Integer.parseInt(s[2])));
											
										}
										
									}
								}
								done=true;
								break;
								
							}
							
						}
						
					
							}
					if(done){
						break;
					}
						}
					}
				
				break;
			case "sell":
				break;
			case "leave":
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
