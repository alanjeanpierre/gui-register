package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.AbstractInventory;
import model.Cart;
import model.ClientInventory;
import model.Inventory;
import server.Client;
import view.RegisterView;

/**
 * Controller for all the item buttons
 * @author Alan Jeanpierre
 *
 */
public class ButtonController implements ActionListener {

	ClientInventory inventory;
	Cart cart;
	RegisterView register;
	
	public ButtonController(ClientInventory inventory2, Cart cart, Client client) {
		// TODO Auto-generated constructor stub
		
		this.inventory = inventory2;
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
	 * on click, adds item to cart and refeshes the cart window
	 */
	@Override
	public void actionPerformed(ActionEvent click) {
		// TODO Auto-generated method stub

		cart.addToCart(click.getActionCommand());
		register.updateCart();
	}

}
