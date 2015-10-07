package ch.ilikechickenwings.TXTRAP;

import java.io.Serializable;
import java.util.ArrayList;

public class Command implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// This class simplifies the use of commands.
	private String command; // interact
	private ArrayList<String> tags = new ArrayList<String>();	// place
	private String tooltip; // Enter places
	private String extra; // possible places are: market, postoffice
	// extra is for extrainformation upon a call like:
	// help interact
	
	public Command(String command, String tag, String tooltip, String extra){
		this.command = command;
		tags.add(tag);
		this.tooltip = tooltip;
		this.extra = extra;
	}
	
	public Command(String command, ArrayList<String> tags, String tooltip, String extra){
		this.command = command;
		this.tags = tags;
		this.tooltip = tooltip;
		this.extra = extra;
	}
	
	public Command(String command, String tag, String tooltip){
		this.command = command;
		this.tooltip = tooltip;
		this.tags.add(tag);
		this.extra = "";
	}
	
	public Command(String command, ArrayList<String> tags, String tooltip){
		this.command = command;
		this.tooltip = tooltip;
		this.tags = tags;
		this.extra = "";
	}
	
	public Command(String command, String tooltip){
		this.command = command;
		this.tooltip = tooltip;
		this.tags = new ArrayList<String>();
		this.extra = "";
	}
	
	// Prints a list of all the commands
	public static void printHelp(ArrayList<Command> commands){
		Console.log("Available commands:",Console.standardOutput);
		for(Command c : commands){
			Console.log(c.getCommand()+" "+c.getTags()+" \t-> "+c.getTooltip(),Console.standardOutput);
		}
	}
	
	// prints the additional help from the command command
	public static void printHelp(String command, ArrayList<Command> commands){
		for(Command c: commands){
			if(c.getCommand().equals(command))
				Console.log(c.getCommand()+" "+c.getTags()+" \t-> "+c.getTooltip()+"\n"+c.getExtra(),Console.standardOutput);
		}
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	public String getTags(){
		String str="";
		for(String tag : tags){
			str+="<"+tag+"> ";
		}
		return str;
	}

	public ArrayList<String> getTagsArrayList() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
}
