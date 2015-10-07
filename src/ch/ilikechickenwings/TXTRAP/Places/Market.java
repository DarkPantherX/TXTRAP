package ch.ilikechickenwings.TXTRAP.Places;

import java.util.ArrayList;

import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.Entity.Human;
import ch.ilikechickenwings.TXTRAP.Entity.Item;
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
						+"\n inventory -> shows your inventory"
						+"\n leave -> Stops the interaction"
						+"\n there may be hungry bandits around to bananize");
					if(!getHumans().isEmpty())
						Console.log("\nThere is a human around");
				break;
			case "showitems":
				Console.log("On the market available is: ", Console.standardOutput);
				
				if(items.size()>0){
					for(Item it : items){
					
					Console.log("->"+it.getName()+" - "+it.getPrice()+" Gold", Console.standardListOutput);
					}
				}else{
					Console.log("-->nothing<-- (market is poor as fuck)", Console.standardListOutput);
				}
				break;
			case "bananize":
				Player p= getWorldFrame().getPlayer();
				boolean tradeDone=false;
				for(Human hu: getHumans()){
					if(hu.getName().equals("Hungry Tom")){
						for(Item item : p.getInventory()){
							if(item.getName().equals("banana") && item.getQuantity()>0){
								item.setQuantity(item.getQuantity()-1);
								p.setHealth(p.getHealth()*5);
								tradeDone=true;
								Console.log("You gave the hungry bandit a banana and feel a boost of life", Console.standardOutput);
							}
						}
					} // end if Hungry Tom
					if(tradeDone)
						getHumans().remove(hu);
				}
					
				break;
			case "buy":
				if(s.length==3){
				boolean done=false;
				ArrayList<Item> pItems=getWorldFrame().getPlayer().getInventory();
					for(Item m : items){
					if(s[1].toLowerCase().equals(m.getName().toLowerCase())){
						for(Item mp : pItems){
							if(mp.getName().toLowerCase().equals("gold")){
								if(mp.getQuantity()>=Integer.parseInt(s[2])*m.getPrice()){
									mp.setQuantity((int)(mp.getQuantity()-Integer.parseInt(s[2])*m.getPrice()));
									
									for(Item nm : pItems){
						
										if(nm.getName().equals(m.getName())){
											nm.setQuantity(nm.getQuantity()+Integer.parseInt(s[2]));
											done=true;
										}
									}
									if(!done){
										pItems.add(new Item(m.getName(),Integer.parseInt(s[2]),m.getPrice()));	
										done=true;
									}
									break;
								}
							
								
							}
							
						}
						
					
							}
					if(done){
						Console.log("You bought "+s[2]+"x "+s[1],Console.standardEvent);
						break;
					}
						}
					
				if(!done){
					Console.log("Can't buy this",Console.errorOutput);
				}
				
				}else{
					Console.log("Commend was used wrong: buy <item> <quantity>",Console.errorOutput);
				}
				break;
			case "sell":
				
				if(s.length==3){
				 boolean done=false;
				ArrayList<Item> pItems=getWorldFrame().getPlayer().getInventory();
					for(Item m : pItems){
					if(s[1].toLowerCase().equals(m.getName().toLowerCase())){
							
								if(m.getQuantity()>=Integer.parseInt(s[2])){
									m.setQuantity(m.getQuantity()-Integer.parseInt(s[2]));
									
									for(Item nm : pItems){
						
										if(nm.getName().toLowerCase().equals("gold")){
											nm.setQuantity((int)(nm.getQuantity()+Integer.parseInt(s[2])*(m.getPrice()/2)));
											done=true;
											break;
										}
									}
									
									
								}
					
							}
					if(done){
						Console.log("You sold "+s[2] +"x "+m.getName());
						break;
					}
						}
					if(!done){
						Console.log("Can't sell item",Console.errorOutput);
					}
				}else{
					Console.log("Commend was used wrong: sell <item> <quantity>",Console.errorOutput);
				}
				
				break;
			case "inventory":
				Console.log("In your Inventory is: ", Console.standardOutput);
					if(getWorldFrame().getPlayer().getInventory().size()>0){
						for(Item mm : getWorldFrame().getPlayer().getInventory()){
						
						if(mm.getQuantity()<0){
							getWorldFrame().getPlayer().getInventory().remove(mm);
				
						}else{
						Console.log("->"+Integer.toString(mm.getQuantity())+"x "+mm.getName(), Console.standardListOutput);
						}
						}
					}else{
						Console.log("-->nothing<-- (poor as fuck...)", Console.standardListOutput);
					}
				
					break;
			case "leave":
				stopInteract(null);
				Console.clearlog();
				getWorldFrame().getPlayer().setPlace(null);
				Console.logSingleLine("You left the market",Console.standardEvent);
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


	/**
	 * @return the items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}


	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

}