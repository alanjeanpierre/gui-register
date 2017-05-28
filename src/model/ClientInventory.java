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

public class ClientInventory extends AbstractInventory {


	/**
	 * Constructor loads data from the file
	 * @param inventoryFile
	 */
	public ClientInventory(String inventoryString ) {
		// TODO Auto-generated constructor stub

		if (inventoryString == null) {
			// bad shit
			System.exit(-1);
		}
		
		Scanner in = new Scanner(inventoryString).useDelimiter("\\||&");
		item2 = new HashMap<>();
		itemNames = new ArrayList<>();
		
		
		updateTime(in.next());
		
		while (in.hasNext()){
			String name = in.next();
			String squantity = in.next();
			String sprice = in.next();
			double price = Double.parseDouble(sprice);
			int quantity = Integer.parseInt(squantity);
			Item temp = new Item(quantity, price, name);
			item2.put(name, temp);
			itemNames.add(name);
		}
		
		
		in.close();
	}
	
	public void updateInventory(String inventoryString) {

		if (inventoryString == null) {
			// bad shit
			System.exit(-1);
		}
		
		Scanner in = new Scanner(inventoryString).useDelimiter("\\||&");
		item2 = new HashMap<>();
		itemNames = new ArrayList<>();
		
		updateTime(in.next());
		
		while (in.hasNext()){
			String name = in.next();
			String squantity = in.next();
			String sprice = in.next();
			double price = Double.parseDouble(sprice);
			int quantity = Integer.parseInt(squantity);
			Item temp = new Item(quantity, price, name);
			item2.put(name, temp);
			itemNames.add(name);
		}
		
		
		in.close();
	}
	

	/**
	 * updates the inventory with from the passed item
	 * @param cart
	 */
	public void updateInventory(Item tempC, String user, Boolean purchase, Boolean stock) {
		

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

	/**
	 * updates the inventory with from the passed cart
	 * @param cart
	 */
	public void updateInventory(AbstractInventory cart, String user, Boolean purchase, Boolean stock) {
		
		
		for (Item tempC : cart.item2.values()) {	
						
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
		
		
	}

}
