package server;

import java.io.IOException;
import java.net.ServerSocket;

import logger.Logger;
import model.Password;
import model.ServerInventory;
import model.Users;

public class Server {
	
	public static final int REQUEST_ERROR_CODE = 400;
	public static final int AUTH_ERROR_CODE = 401;
	public static final int OK_CODE = 200;
	public static final int NOT_IMPLEMENTED_CODE = 501;
	public static final int NOT_ENOUGH_STOCK = 402;
	public static final int NOT_CURRENT = 502; 

	public static void main(String args[]) {
		int port = Integer.parseInt(args[0]);
		String inventoryFile = args[1];
		
		ServerInventory inventory = new ServerInventory(inventoryFile);
		Password password = new Password();
		password.loadPassword("partsbin");
		
		Users users = new Users();
		Logger.startup();
		
		
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Running on " + serverSocket.getInetAddress());
			while (true) {
				Connection client = new Connection(serverSocket.accept(), inventory, users, password);
				new Thread(client).start();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
