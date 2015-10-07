package ch.ilikechickenwings.TXTRAP.Frames;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.NetInput;

public class OnlineFrame implements Processable, Runnable{

	private MainFrame mainFrame;
	public String ip;
	public int port;
	private boolean connected;
	
	private boolean asdf = true;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	private Socket socket;
	Input ir;
	volatile Thread il;


	
	private ArrayList<NetInput> ins = new ArrayList<NetInput>();
	
	
	public OnlineFrame(MainFrame mF){
		setMainFrame(mF);

	}
	
	public void getOnline() {
		
			try {
				socket = new Socket(ip, port);
				connected=true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			new Thread(this).start();

		
		}
	
	@Override
	public void processInput(String[] s) {
		
		if(connected){
		
		StringBuilder builder = new StringBuilder();
		for(String str : s) {
		    builder.append(str+" ");
		}
		ins.add(new NetInput(builder.toString()));
		}else{
			
			if(s[0].toLowerCase().equals("return")){
				mainFrame.setProcessable(mainFrame);
				Console.log("Available Commands: online/new game/load <gamename>");
			}else{
				Console.log("type 'return' to return to the main menu, are didnt connecnt");
			}
			
		}
	}

	
	@Override
	public void run() {
		try {

			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			NetInput p = new NetInput("TEXT");
			ins.add(p);

			ir=new Input();
			il =new Thread(ir);
			il.start();

			while (asdf) {

				// System.out.println(mes);

				if (!ins.isEmpty()) {
					sendMessage(((NetInput)ins.get(0)));
					ins.remove(0);

				}

				Thread.sleep(50L);

			}

		} catch (UnknownHostException e) {
			System.out.println("Can not connect to host");
			close();
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: IP");
			close();
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
			close();
		}
	}

	private void sendMessage(NetInput netInput) {
		if (socket.isConnected()) {
			try {
				oos.writeObject(netInput);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			try {
				oos.close();
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		
	}

	synchronized void close() {
			
		try {
				ir.kill();
				connected=false;
				
				socket.close();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
			
			asdf = false;
			
			Console.log(("Disconnected From Server"));
		
			

		}

	
	/**
	 * @return the mainFrame
	 */
	public MainFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * @param mainFrame the mainFrame to set
	 */
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	
	



class Input implements Runnable {
		
		private volatile boolean isRunning = true;
		@Override
		public void run() {
			
			while (isRunning) {
				try {
					if (ois != null) {
						if(connected){
						NetInput net = (NetInput) ois.readObject();
						if(net.isClearTxt()){
							Console.clearlog();
							Console.logSingleLine(net.getTxt(), net.getStyle());
						}else if(net.isSingleLine()){
							Console.logSingleLine(net.getTxt(),net.getStyle());
						}else{
							Console.log(net.getTxt(), net.getStyle());
							
						}
						}
						

						
						}
					Thread.sleep(50L);
					
				} catch (EOFException e) {
					close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

			
				
			}

		}
		
		public void kill(){
			isRunning=false;
		}
	}

	
	
	
	
}
