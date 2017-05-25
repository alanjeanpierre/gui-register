package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

import logger.Logger;
import model.Cart;
import model.Inventory;
import model.Item;
import model.Password;
import model.ServerInventory;
import model.Users;

public class Connection implements Runnable {

	private Socket client;
	private PrintWriter out;
	private BufferedReader in;
	private ServerInventory inventory;
	private Users users;
	private Password password;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			out = new PrintWriter(client.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			out.println("You're in");
			Logger.connect(client.getInetAddress());

			String input;
			while ((input = in.readLine()) != null) {
				System.out.println(input);
				processInput(input);
			}

		}
		catch (IOException e) {
			e.printStackTrace();
			try {
				client.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				// shits real bad if we're here
			}
			System.exit(-1);
		}
		
	}
	
	/**
	 * lets design that api
	 * delimted by |
	 * keywords:
	 * 		addstock	-- adds an item			addstock|user|password|item|num
	 * 		* 						
	 * 		adduser		-- adds a user			adduser|user|password|newuser
	 * 			
	 * 		buy			-- buys item			buy|user|timestamp|item|quant&item|...
	 * 
	 * 		setstock	-- sets item			setstock|user|password|item|num
	 * 
	 * 		setprice 	-- sets price of item	setprice|user|password|item|price
	 * 
	 * 		getinv		-- gets inventory		getinv
	 * 					returns inventory, separated by & per item in the format
	 * 					timestamp|item1|num1|price1&item2|num2|price2&...
	 * 
	 * 		update		-- checks for updates	update|timestamp
	 * 					returns "up to date" or "not up to date"
	 * 		
	 * 
	 * 		return codes
	 * 			200		-- ok
	 * 			400		-- bad request
	 * 			401		-- bad auth
	 * 			402		-- invalid stock
	 * 			501 	-- Not implemnted yet
	 *		
	 * @param input
	 * @param out 
	 */
	private void processInput(String input) {
		Scanner parser = new Scanner(input).useDelimiter("\\|");
		
		if (!parser.hasNext()) {
			out.println(Integer.toString(Server.REQUEST_ERROR_CODE));
			return;
		}
		
		String op = parser.next();
		String user, pass, item, newUser;
		int num;
		double price;
		Item temp;
		
		switch(op) {
			case "addstock":
				try {
					user = parser.next();
					pass = parser.next();
					item = parser.next();
					num = parser.nextInt();
				}
				catch (NoSuchElementException e) {
					out.println(Integer.toString(Server.REQUEST_ERROR_CODE));
					return;
				}
				temp = new Item(inventory.getItem(item));
				temp.setQuantity(num);
				addStock(temp, user, pass);
				break;
				
			case "adduser":
				try {
					user = parser.next();
					pass = parser.next();
					newUser = parser.next();
				}
				catch (NoSuchElementException e) {
					out.println(Integer.toString(Server.REQUEST_ERROR_CODE));
					return;
				}
				addUser(user, pass, newUser);
				break;
				
			case "buy":
				Cart cart = new Cart(inventory);
				LocalTime timestamp;
				try {
					user = parser.next();
					timestamp = LocalTime.parse(parser.next());
					while (parser.hasNext()) {
						item = parser.next();
						num = parser.nextInt();
						Item tmp = new Item(inventory.getItem(item));
						tmp.setQuantity(num);
						cart.addItem(tmp);
					}
				}
				catch (NoSuchElementException e) {
					out.println(Integer.toString(Server.REQUEST_ERROR_CODE));
					return;
				}
				
				buy(cart, user, timestamp);
				break;
				
			case "setstock":
				try {
					user = parser.next();
					pass = parser.next();
					item = parser.next();
					num = parser.nextInt();
				}
				catch (NoSuchElementException e) {
					out.println(Integer.toString(Server.REQUEST_ERROR_CODE));
					return;
				}
				temp = new Item(inventory.getItem(item));
				temp.setQuantity(num);
				setStock(temp, user, pass);
				break;
				
			case "setprice":
				try {
					user = parser.next();
					pass = parser.next();
					item = parser.next();
					price = parser.nextDouble();
				}
				catch (NoSuchElementException e) {
					out.println(Integer.toString(Server.REQUEST_ERROR_CODE));
					return;
				}
				temp = inventory.getItem(item);
				temp.setPrice(price);
				setStock(temp, user, pass);
				break;
				
			case "getinv":
				getinv();
				break;
			case "update":
				update();
				break;
				
			default:	// bad request
				out.println(Integer.toString(Server.REQUEST_ERROR_CODE));
				return;
		}
		
		
		
	}
	

	private void update() {
		// TODO Auto-generated method stub
		out.println(Integer.toString(Server.NOT_IMPLEMENTED_CODE));
	}

	private void getinv() {
		out.println(inventory.getAPIInventory());				
	}

	private void setStock(Item item, String user, String pass) {
		
		if (password.checkPassword(pass) && users.checkUser(user)) {
			inventory.updateInventory(item, user, false, true);
			out.println(Integer.toString(Server.OK_CODE));
		}
		else {
			out.println(Integer.toString(Server.AUTH_ERROR_CODE));
		}
		
		
	}

	private void buy(Cart cart, String user, LocalTime timestamp) {

		if (users.checkUser(user)) {
			
			if (!inventory.checkCurrent(timestamp)) {
				out.println(Integer.toString(Server.NOT_CURRENT));
				return;
			}
			
			if (inventory.checkCart(cart) != null) {
				out.println(Integer.toString(Server.NOT_ENOUGH_STOCK));
				return;
			}
			
			inventory.updateInventory(cart, user, false, false);
			out.println(Integer.toString(Server.OK_CODE));
		}
		else {
			out.println(Integer.toString(Server.AUTH_ERROR_CODE));
		}
		
	}

	private void addUser(String user, String pass, String newUser) {
		
		if (password.checkPassword(pass) && (users.checkUser(user) || users.isEmpty() ) ) {
			users.add(newUser);
			out.println(Integer.toString(Server.OK_CODE));
		}
		else {
			out.println(Integer.toString(Server.AUTH_ERROR_CODE));
		}
		
	}
	
	private void addStock(Item item, String user, String pass) {

		if (password.checkPassword(pass) && users.checkUser(user)) {
			inventory.updateInventory(item, user, false, false);
			out.println(Integer.toString(Server.OK_CODE));
		}
		else {
			out.println(Integer.toString(Server.AUTH_ERROR_CODE));
		}
		
	}

	public Connection (Socket client, ServerInventory inventory2, Users users, Password password) {
		this.client = client;
		this.inventory = inventory2;
		this.users = users;
		this.password = password;
	}

}
