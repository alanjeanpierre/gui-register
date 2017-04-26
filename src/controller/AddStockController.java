package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.*;
import model.*;

/**
 * Controls the add stock button. Checks for a valid user, then 
 * launches the add stock window
 * @author Alan Jeanpierre
 *
 */
public class AddStockController implements ActionListener {
	
	private Users users;
	private Inventory inventory;
	private Cart cart;
	private RegisterView register;
	private Password password;
	

	
	public AddStockController(Users users, Inventory inventory, Cart cart, Password password) {
		// TODO Auto-generated constructor stub
		this.users = users;
		this.inventory = inventory;
		this.cart = cart;
		this.password = password;
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
		if (users.checkUser()) {
			cart.clear();
			register.updateCart();
			
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						AddStock window = new AddStock(inventory, users, password);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		else {
			TextWindow.main(users.getCurrentUser(), "Error: Invalid User");
		}
		
	}

}
