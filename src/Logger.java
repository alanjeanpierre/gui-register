import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.*;

public class Logger {

	private static final String log = "log.csv";
	private static double sales = 0;
	
	public static void startup() {
		
		try{
		    PrintWriter writer = new PrintWriter(new FileOutputStream(new File(log),true));
		    
		    //date, time, (user), function
		    writer.format("%s,%s,,STARTUP,\n", 
		    		LocalDate.now(), LocalTime.now());

		    writer.close();
		} catch (IOException e) {
		    System.err.println("failed to open log file");
		    System.exit(1);
		}
	}
	
	public static void buy(String user, Item item) {
		try{
		    PrintWriter writer = new PrintWriter(new FileOutputStream(new File(log),true));
		    double sale = item.getPrice() + item.getQuant();
		    //date, user, item, quantity, price, total
		    writer.format("%s,%s,%s,PURCHASE,%s,%s,%.2f,%.2f\n", 
		    		LocalDate.now(), LocalTime.now(),
		    		user, item.getName(), item.getQuant(), item.getPrice(), sale);
		    
		    sales += sale;

		    writer.close();
		} catch (IOException e) {
		    System.err.println("failed to open log file");
		    System.exit(1);
		}
	}
	
	public static void stock(String user, Item item) {
		try{
		    PrintWriter writer = new PrintWriter(new FileOutputStream(new File(log),true));
		    
		    //date, user, item, quantity, price, total
		    writer.format("%s,%s,%s,STOCK,%s,%s,%.2f,%.2f\n", 
		    		LocalDate.now(), LocalTime.now(), 
		    		user, item.getName(), item.getQuant(), item.getPrice(), 
		    		item.getPrice() * item.getQuant());

		    writer.close();
		} catch (IOException e) {
		    System.err.println("failed to open log file");
		    System.exit(1);
		}
	
	}
	
	public static void popup(String user, String message) {
		
		try{
		    PrintWriter writer = new PrintWriter(new FileOutputStream(new File(log),true));
		    
		    //date, user, item, quantity, price, total
		    writer.format("%s,%s,%s,MESSAGE,%s\n", 
		    		LocalDate.now(), LocalTime.now(), user, message);

		    writer.close();
		} catch (IOException e) {
		    System.err.println("failed to open log file");
		    System.exit(1);
		}
	}
	
	public static void close() {
		
		try{
		    PrintWriter writer = new PrintWriter(new FileOutputStream(new File(log),true));
		    
		    writer.format("%s,%s,,TOTAL,%.2f\n",
		    		LocalDate.now(), LocalTime.now(),
		    		sales);
		    
		    //date, time, (user), function
		    writer.format("%s,%s,,CLOSE,\n", 
		    		LocalDate.now(), LocalTime.now());

		    writer.close();
		} catch (IOException e) {
		    System.err.println("failed to open log file");
		    System.exit(1);
		}
	}
}