package model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import logger.Logger;

public class ServerInventory extends AbstractInventory {

	private String file;
	private LocalTime lastUpdate;

	/**
	 * Constructor loads data from the file
	 * @param inventoryFile
	 */
	public ServerInventory(String inventoryFile ) {
		// TODO Auto-generated constructor stub

		Scanner scanner = null;
		
		file = inventoryFile;
		
		try {
		    scanner = new Scanner(new File(inventoryFile));
		} catch (Exception FileNotFoundException) {
		    System.err.printf("failed to open %s\n", inventoryFile);
		    System.exit(1);
		}
		Scanner in = scanner.useDelimiter("[,|\n\r]+");
		
		in.nextLine();
		in.nextLine();
		
		item2 = new HashMap<>();
		itemNames = new ArrayList<>();
		
		while (in.hasNext()){
			String name = in.next();
			String sprice = in.next();
			String squantity = in.next();
			double price = Double.parseDouble(sprice);
			int quantity = Integer.parseInt(squantity);
			Item temp = new Item(quantity, price, name);
			item2.put(name, temp);
			itemNames.add(name);
		}
		
		
		scanner.close();
		in.close();
		
		updateTime();
	}
	
	public String getAPIInventory() {
		StringBuilder s = new StringBuilder();

		s.append(lastUpdate.toString() + "|");
		for (Item i : item2.values()) {
			s.append(i.apiString() + "&");
		}
		
		
		
		return s.toString();
	}

	/**
	 * updates the inventory with from the passed item
	 * @param cart
	 */
	public void updateInventory(Item tempC, String user, Boolean purchase, Boolean stock) {
		
		
			if (purchase) {
				Logger.buy(user, tempC);
			}
			else {
				if (stock)
					Logger.stock(user, tempC, true);
				else 
					Logger.stock(user,  tempC, false);
			}
						
			if (item2.containsKey(tempC.getName())) {
				
				Item tmp = item2.get(tempC.getName());
				if (!stock)
					tmp.addQuantity(-tempC.getQuant());
				tmp.setPrice(tempC.getPrice());
				item2.replace(tmp.getName(), tmp);
			} 
			else {
				item2.put(tempC.getName(), tempC);
			}
			
			updateTime();
	}

	/**
	 * updates the inventory with from the passed cart
	 * @param cart
	 */
	public void updateInventory(AbstractInventory cart, String user, Boolean purchase, Boolean stock) {
		
		
		for (Item tempC : cart.item2.values()) {	
			if (purchase) {
				Logger.buy(user, tempC);
			}
			else {
				if (stock)
					Logger.stock(user, tempC, true);
				else 
					Logger.stock(user,  tempC, false);
			}
						
			if (item2.containsKey(tempC.getName())) {
				
				Item tmp = item2.get(tempC.getName());
				if (!stock)
					tmp.addQuantity(-tempC.getQuant());
				tmp.setPrice(tempC.getPrice());
				item2.replace(tmp.getName(), tmp);
			} 
			else {
				item2.put(tempC.getName(), tempC);
			}
		}
		
		updateTime();
		
	}

	/** 
	 * opens and updates the inventory CSV
	 */
	public void updateCSV() {
		try{
		    PrintWriter writer = new PrintWriter(file);
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
		    System.err.printf("failed to open %s whyy\n", file);
		    System.exit(1);
		}
	}

	private void updateTime() {
		// TODO Auto-generated method stub
		lastUpdate = LocalTime.now();
		
	}
	
	public boolean checkCurrent(LocalTime timestamp) {
		return lastUpdate.equals(timestamp);
	}
	
	public Item checkCart(AbstractInventory cart) {		
		for (Item c : cart.item2.values()) {
			if (item2.get(c.getName()).getQuant() - c.getQuant() < 0)
				return c;
		}
		
		return null;
	}

}
