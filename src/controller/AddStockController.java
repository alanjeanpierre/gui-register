package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.*;
import model.*;
import server.Client;

/**
 * Controls the add stock button. Checks for a valid user, then 
 * launches the add stock window
 * @author Alan Jeanpierre
 *
 */
public class AddStockController implements ActionListener {
	
	private Client client;
	private ClientInventory inventory;
	private Cart cart;
	private RegisterView register;
	

	
	public AddStockController(ClientInventory inventory2, Cart cart, Client client) {
		// TODO Auto-generated constructor stub
		this.inventory = inventory2;
		this.cart = cart;
		this.client = client;
	}
	
	/**
	 * Adds register reference to ease initialization
	 * @param register
	 */
	public void registerRegister(RegisterView register) {
		this.register = register;
	}

	/**
	 * Checks user and launches stock window, 
	 * or launches error window
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (client.auth(register.getUser())) {
			cart.clear();
			register.updateCart();
			
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						AddStock window = new AddStock(inventory, register.getUser(), client);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		else {
			TextWindow.main(register.getUser(), "Error: Invalid User");
		}
		
	}

}
