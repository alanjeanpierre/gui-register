package main;

import java.awt.EventQueue;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import logger.Logger;

import model.Cart;
import model.ClientInventory;
import model.Inventory;
import model.Password;
import model.AbstractInventory;
import model.Users;
import server.Client;
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
		

		Client client = new Client(args[0], Integer.parseInt(args[1]));
		
		ClientInventory inventory = new ClientInventory(client.getinv());
		
		Cart cart = new Cart(inventory);
		
		Password p = new Password();
		
		AddStockController stock = new AddStockController(inventory, cart, client);
		ButtonController btn = new ButtonController(inventory, cart, client);
		PurchaseController purchase = new PurchaseController(inventory, cart, client);
		UsernameListener ulist = new UsernameListener();
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterView window = new RegisterView(inventory, cart);	
					window.registerListeners(btn, stock, purchase, ulist);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		
		
	}

}
