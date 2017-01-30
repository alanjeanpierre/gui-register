import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class Inventory {


	private HashMap<String, Item> item2;
	
	/**
	 * initializes with an empty inventory hashmap
	 */
	public Inventory () {
		item2 = new HashMap<>();
	}
	
	/**
	 * loads inventory from file
	 * @param file
	 */
	public Inventory(String file) {
		Scanner scanner = null;
		
		try {
		    scanner = new Scanner(new File(file));
		} catch (Exception FileNotFoundException) {
		    System.err.println("failed to open inventory.csv");
		    System.exit(1);
		}
		Scanner in = scanner.useDelimiter("[,|\n\r]+");
		
		in.nextLine();
		in.nextLine();
		
		item2 = new HashMap<>();
		
		while (in.hasNext()){
			String name = in.next();
			String sprice = in.next();
			String squantity = in.next();
			double price = Double.parseDouble(sprice);
			int quantity = Integer.parseInt(squantity);
			Item temp = new Item(quantity, price, name);
			item2.put(name, temp);
		}
		
		scanner.close();
		in.close();
	}
	
	/**
	 * adds item of single quantity to inventory
	 * @param name
	 * @param price
	 */
	public void addItem(String name, double price) {
		
		if (item2.containsKey(name)) {
			Item temp = item2.get(name);
			temp.addQuantity(1);
			item2.replace(name, temp);
		}
		else {
			item2.put(name, new Item(1, price, name));
		}
		
	}
	
	/**
	 * adds item of the passed item to inventory
	 * @param item
	 */
	public void addItem(Item item) {
		if (item2.containsKey(item.getName())) {
			Item temp = item2.get(item.getName());
			temp.addQuantity(item.getQuant());
			item2.replace(temp.getName(), temp);
			
			System.out.println(item2.get(item.getName()).getQuant());
		}
		else {
			item2.put(item.getName(), item);
		}
		
	}
	
	/** returns item
	 * 
	 * @param name
	 * @return
	 */
	public Item getItem(String name) {
		
		return new Item(item2.get(name));
		
	}
	
	
	/**
	 * clears inventory and sets it to cart
	 * @param cart
	 */
	public void setInventory(Inventory cart) {
		item2.clear();
		for (Item item : cart.item2.values()) {

			Logger.buy("", item);
			item2.put(item.getName(), item);
		}
	}
	
	/**
	 * checks to see if subtracting the cart from the inventory
	 * will make any quantities go negative, and returns the one that does
	 * @param cart
	 * @return
	 */

	public Item checkCart(Inventory cart) {		
		for (Item c : cart.item2.values()) {
			if (item2.get(c.getName()).getQuant() - c.getQuant() < 0)
				return c;
		}
		
		return null;
	}
	
	/**
	 * updates the inventory with from the passed cart
	 * @param cart
	 */
	public void updateInventory(Inventory cart, String user, Boolean purchase) {
		for (Item tempC : cart.item2.values()) {
			
			
			if (purchase) Logger.buy(user, tempC);
			else Logger.stock(user, tempC);
			
			
			if (item2.containsKey(tempC.getName())) {
				Item tmp = item2.get(tempC.getName());
				
				tmp.addQuantity(-tempC.getQuant());
				tmp.setPrice(tempC.getPrice());
				
				
				item2.replace(tmp.getName(), tmp);
				
			}
			else{
				item2.put(tempC.getName(), tempC);
			}
		}
		
	}
	
	/**
	 * clears the hashmap inventory
	 */
	public void clear() {
		item2.clear();
	}
	
	/**
	 * iterates over the inevntory and returns the total price
	 * @return
	 */
	public double getTotal() {
		
		double total = 0;
		
		for (Item temp : item2.values()) {
			total += temp.getPrice() * temp.getQuant();
		}
		
		return total;
	}
	
	public String print() {
		String str = new String();
		
		for (Item temp : item2.values()) {
			str = str.concat(String.format("%-15s %d@$%.2f  %.2f\n", temp.getName(), temp.getQuant(), temp.getPrice(), temp.getQuant()*temp.getPrice()));
		}

		return str;
	}
	
	public void updateCSV() {
		try{
		    PrintWriter writer = new PrintWriter("inventory.csv");
		    writer.println("Inventory,,");
		    writer.println("name,price,quantity");
		    
		    for (Item temp : item2.values()) {
		    	String name = temp.getName();
		    	String quantity = Integer.toString(temp.getQuant());
		    	String price = Double.toString(temp.getPrice());
		    	String out = name + "," + price + "," + quantity;
		    	
		    	writer.println(out);
		    }

		    writer.close();
		} catch (IOException e) {
		    System.err.println("failed to open inventory.csv whyy");
		    System.exit(1);
		}
	}
	
	
}
