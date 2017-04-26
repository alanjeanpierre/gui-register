package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Cart;
import model.Inventory;
import model.Item;
import model.Users;
import view.RegisterView;
import view.TextWindow;

/**
 * Controller for the purchase and cancel buttons
 * @author Alan Jeanpierre
 *
 */
public class PurchaseController implements ActionListener {

	private Users users;
	private Inventory inventory;
	private Cart cart;
	private RegisterView register;
	
	public PurchaseController(Users users, Inventory inventory, Cart cart) {
		this.users = users;
		this.inventory = inventory;
		this.cart = cart;
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
		
		if (users.checkUser()) {
			
			Item c = inventory.checkCart(cart);
			
			if (c != null) {
				TextWindow.main(users.getCurrentUser(), String.format("Error: not enough stock for %s", c.getName()));
				return;
			}
			
			
			inventory.updateInventory(cart, users.getCurrentUser(), true);
			cart.clear();
			register.updateCart();
			inventory.updateCSV();
			register.updateHoverText();
			//open drawer
		}
		else {
			TextWindow.main(users.getCurrentUser(), "Error: Invalid User");
		}
		
	}

}
