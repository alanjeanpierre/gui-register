import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class Inventory {

	private LinkedList<Item> items = new LinkedList<Item>();
	
	public Inventory() {
		Scanner scanner = null;
		
		try {
		    scanner = new Scanner(new File("inventory.csv"));
		} catch (Exception FileNotFoundException) {
		    System.err.println("failed to open inventory.csv");
		    System.exit(1);
		}
		Scanner in = scanner.useDelimiter("[,|\n\r]+");
		
		in.nextLine();
		in.nextLine();
		
		while (in.hasNext()){
			String name = in.next();
			String sprice = in.next();
			String squantity = in.next();
			double price = Double.parseDouble(sprice);
			int quantity = Integer.parseInt(squantity);
			Item temp = new Item(quantity, price, name);
			items.add(temp);
		}
		
		scanner.close();
		in.close();
	}
	
	public Item getItem(String name) {
		for (Item temp : items) {
			if (temp.getName().equals(name))
			{
				return new Item(temp);
			}
		}
		
		return null;
	}
	
	public LinkedList<Item> getList() {
		return items;
	}
	
	public Item checkCart(LinkedList<Item> cart) {
		for (Item c : cart) {
			for (Item i : items) {
				if (i.getName().equals(c.getName())) {
					if (i.getQuant() - c.getQuant() < 0)
						return c;
				}
			}
		}
		
		return null;
	}
	
	public void updateInventory(LinkedList<Item> cart) {
		for (Item tempC : cart) {
			for (Item tempI: items) {
				if (tempC.getName().equals(tempI.getName())) {
					tempI.addQuantity(-tempC.getQuant());
					tempI.setPrice(tempC.getPrice());
				}
			}
		}
	}
	
	public void updateCSV() {
		try{
		    PrintWriter writer = new PrintWriter("inventory.csv");
		    writer.println("﻿Inventory,,");
		    writer.println("name,price,quantity");
		    for (int i = 0; i < items.size(); i++) {
		    	Item temp = items.get(i);
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
