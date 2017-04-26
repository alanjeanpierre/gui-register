package main;

import java.awt.EventQueue;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import logger.Logger;

import model.Cart;
import model.Inventory;
import model.Password;
import model.AbstractInventory;
import model.Users;
import view.AddStock;
import view.RegisterView;
import controller.*;

/**
 * Main class to start up the various components 
 * and controllers
 * @author Alan Jeanpierre
 *
 */
public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String inventoryFile;
		
		Users users = new Users();
		
		
		Scanner scanner = null;
		
		try {
		    scanner = new Scanner(new File("setup"));
		} catch (Exception FileNotFoundException) {
		    System.err.println("failed to open setup file, try running setup first");
		    System.exit(1);
		}
		Scanner in = scanner.useDelimiter("[\\,\\n\\r]+");
		
		
	   
		int password = in.nextInt();
		if (args.length > 0) {
			inventoryFile = args[0];
			in.next();
		}
		else {
			inventoryFile = in.next();
		}

		while (in.hasNext()) {
			users.add(in.nextInt());		
			
		}
		
		in.close();
		scanner.close();
		
		Inventory inventory = new Inventory(inventoryFile);

		Cart cart = new Cart(inventory);
		
		Password p = new Password();
		
		AddStockController stock = new AddStockController(users, inventory, cart, p);
		ButtonController btn = new ButtonController(inventory, cart);
		PurchaseController purchase = new PurchaseController(users, inventory, cart);
		UsernameListener ulist = new UsernameListener(users);
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterView window = new RegisterView(inventory, cart, users);	
					window.registerListeners(btn, stock, purchase, ulist);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		
		Logger.startup();
		
	}

}
