package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Cart;
import model.ClientInventory;
import model.Inventory;
import model.Item;
import model.Users;
import server.Client;
import view.RegisterView;
import view.TextWindow;

/**
 * Controller for the purchase and cancel buttons
 * @author Alan Jeanpierre
 *
 */
public class PurchaseController implements ActionListener {

	private ClientInventory inventory;
	private Cart cart;
	private RegisterView register;
	private Client client;
	
	public PurchaseController(ClientInventory inventory, Cart cart, Client client) {
		this.inventory = inventory;
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
	 * If cancel, clear cart and refresh
	 * If purchase, check user and update the inventory,
	 * clear cart, update the inventory file
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getActionCommand().equals("Cancel")) {
			cart.clear();
			register.updateCart();
			return;
		}
		
		if (client.auth(register.getUser())) {
			
			
			cart.updateTime(inventory.getTime());
			int response = client.write("buy|" + register.getUser() + "|_|" + cart.getAPIInventory());

			inventory.updateInventory(client.getinv());
			if (response == 502) {
				// not up to date;
				return;
			}
			if (response == 402) {
				// not enough stock;
				TextWindow.main(register.getUser(), "Not enough stock");
			}

			//inventory.updateInventory(cart, register.getUser(), true, false);
			
			cart.clear();
			register.updateCart();
			register.updateHoverText();
			//open drawer
		}
		else {
			TextWindow.main(register.getUser(), "Error: Invalid User");
		}
		
	}

}
