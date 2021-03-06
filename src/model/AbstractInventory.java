package model;
import java.io.File;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.*;

import logger.Logger;


/**
 * Abstract inventory class
 * @author Alan Jeanpierre
 *
 */
public abstract class AbstractInventory {


	protected HashMap<String, Item> item2;
	protected ArrayList<String> itemNames;
	private LocalTime lastUpdate;
	
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
		if (item2.get(name) == null) return null;
		return new Item(item2.get(name));
		
	}
	

	
	public int getNumOfItems() {
		return item2.size();
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
	
	/**
	 * gets Item name from index
	 * @param index
	 * @return
	 */
	public String getItemName(int index) {
		return itemNames.get(index);
	}
	

	public String getAPIInventory() {
		StringBuilder s = new StringBuilder();

		s.append(lastUpdate.toString() + "|");
		for (Item i : item2.values()) {
			s.append(i.apiString() + "&");
		}
		
		
		
		return s.toString();
	}

	protected void updateTime() {
		// TODO Auto-generated method stub
		lastUpdate = LocalTime.now();
		
	}
	
	public void updateTime(String t) {
		lastUpdate = LocalTime.parse(t);
	}
	
	public String getTime() {
		return lastUpdate.toString();
	}
	
	public boolean checkCurrent(LocalTime timestamp) {
		return lastUpdate.equals(timestamp);
	}
	
}
