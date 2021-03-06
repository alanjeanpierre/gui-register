package logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.time.*;

import model.Item;

/**
 * Logger class for the register
 * Logs stock purchases, sales, errors, totals, and
 * startup/shut down times
 * @author Alan Jeanpierre
 *
 */
public class Logger {

	private static final String log = "log.csv";
	
	
	/**
	 * logs time of startup
	 */
	public static void startup() {
		
		new File("logs").mkdir();
		
		try{
		    PrintWriter writer = new PrintWriter(new FileOutputStream(new File("./logs/" + LocalDate.now()+"-"+log),true));
		    
		    //date, time, (user), function
		    writer.format("%s,%s,,STARTUP,\n", 
		    		LocalDate.now(), LocalTime.now());

		    writer.close();
		} catch (IOException e) {
		    System.err.println("failed to open log file");
		    System.exit(1);
		}
	}
	
	/**
	 * logs items bought by which user at what time
	 * @param user
	 * @param item
	 */
	public static void buy(String user, Item item) {
		try{
		    PrintWriter writer = new PrintWriter(new FileOutputStream(new File("./logs/" + LocalDate.now()+"-"+log),true));
		    double sale = item.getPrice() * item.getQuant();
		    //date, user, item, quantity, price, total
		    writer.format("%s,%s,%s,PURCHASE,%s,%s,%.2f,%.2f\n", 
		    		LocalDate.now(), LocalTime.now(),
		    		user, item.getName(), item.getQuant(), item.getPrice(), sale);
		    
		    writer.close();
		} catch (IOException e) {
		    System.err.println("failed to open log file");
		    System.exit(1);
		}
	}
	
	/**
	 * logs items added by which user at what time
	 * @param user
	 * @param item
	 */
	public static void stock(String user, Item item, Boolean reset) {
		
		if (!reset && item.getQuant() == 0 ) {
			return;
		}
		else {
			try{
			    PrintWriter writer = new PrintWriter(new FileOutputStream(new File("./logs/" + LocalDate.now()+"-"+log),true));
			    int quant = Math.abs(item.getQuant());
			    
			    //date, user, item, quantity, price, total
			    writer.format("%s,%s,%s,STOCK,%s,%s,%.2f,%.2f,%s%s\n", 
			    		LocalDate.now(), LocalTime.now(), 
			    		user, item.getName(), quant, item.getPrice(), 
			    		item.getPrice() * quant, "RESET:", reset);

			    writer.close();
			} catch (IOException e) {
			    System.err.println("failed to open log file");
			    System.exit(1);
			}
		}
		
	
	}
	
	public static void resetStock(String user) {

		try{
		    PrintWriter writer = new PrintWriter(new FileOutputStream(new File("./logs/" + LocalDate.now()+"-"+log),true));
		    
		    //date, user, item, quantity, price, total
		    writer.format("%s,%s,%s,RESETSTOCK\n", 
		    		LocalDate.now(), LocalTime.now(), user);

		    writer.close();
		} catch (IOException e) {
		    System.err.println("failed to open log file");
		    System.exit(1);
		}
	}
	
	
	/**
	 * logs any popups and their messages
	 * @param user
	 * @param message
	 */
	/*
	public static void popup(String user, String message) {
		
		try{
		    PrintWriter writer = new PrintWriter(new FileOutputStream(new File("./logs/" + LocalDate.now()+"-"+log),true));
		    
		    //date, user, item, quantity, price, total
		    writer.format("%s,%s,%s,MESSAGE,%s\n", 
		    		LocalDate.now(), LocalTime.now(), user, message);

		    writer.close();
		} catch (IOException e) {
		    System.err.println("failed to open log file");
		    System.exit(1);
		}
	}
	*/
	/**
	 * logs new connection to server
	 */
	public static void connect(InetAddress address) {
		
		try{
		    PrintWriter writer = new PrintWriter(new FileOutputStream(new File("./logs/" + LocalDate.now()+"-"+log),true));
		    
		    //date, time, (user), function
		    writer.format("%s,%s,,CONNECT,%s\n", 
		    		LocalDate.now(), LocalTime.now(), address.toString());

		    writer.close();
		} catch (IOException e) {
		    System.err.println("failed to open log file");
		    System.exit(1);
		}
		
	}
	
	/**
	 * logs the total sales for that app period and the time of close
	 */
	public static void close() {
		
		try{
		    PrintWriter writer = new PrintWriter(new FileOutputStream(new File("./logs/" + LocalDate.now()+"-"+log),true));
		    
		    /*
		    writer.format("%s,%s,,TOTAL,%.2f\n",
		    		LocalDate.now(), LocalTime.now(),
		    		sales);
		    */
		    //date, time, (user), function
		    writer.format("%s,%s,,CLOSE,\n", 
		    		LocalDate.now(), LocalTime.now());

		    writer.close();
		} catch (IOException e) {
		    System.err.println("failed to open log file");
		    System.exit(1);
		}
	}

	public static void code(int code, String request, InetAddress inetAddress) {
		// TODO Auto-generated method stub

		try{
		    PrintWriter writer = new PrintWriter(new FileOutputStream(new File("./logs/" + LocalDate.now()+"-"+log),true));
		    
		    //date, user, item, quantity, price, total
		    writer.format("%s,%s,%s,Code,%s,%s\n", 
		    		LocalDate.now(), LocalTime.now(), inetAddress.toString(), code, request);

		    writer.close();
		} catch (IOException e) {
		    System.err.println("failed to open log file");
		    System.exit(1);
		}
	}
}
