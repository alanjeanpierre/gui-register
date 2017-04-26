package model;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Inventory implementation as a cart
 * @author Alan Jeanpierre
 *
 */
public class Cart extends AbstractInventory {
	
	private Inventory inventory;

	public Cart(Inventory inventory) {
		this.inventory = inventory;
		item2 = new HashMap<>();
		itemNames = new ArrayList<>();
	}
	
	/**
	 * returns a cart-printing friendly string
	 * @return
	 */
	public String print() {
		String str = new String();
		
		for (Item temp : item2.values()) {
			str = str.concat(String.format("%-15s %d@$%.2f  %.2f\n", temp.getName(), temp.getQuant(), temp.getPrice(), temp.getQuant()*temp.getPrice()));
		}

		return str;
	}
	
	/**
	 * adds item to cart based on name
	 * @param name
	 */
	public void addToCart(String name) {
		
		Item temp = inventory.getItem(name);
		temp.singleQuantity();

		addItem(temp);
	}
}
