import java.math.BigDecimal;

public class Item {

	private int quantity;
	private BigDecimal price;
	private String name;
	
	/**
	 * constructing from a list of parameters
	 * @param quantity
	 * @param price
	 * @param name
	 */
	public Item(int quantity, double price, String name) {
		this.quantity = quantity;
		this.price = new BigDecimal(price);
		this.name = name;
	}
	
	/**
	 * cloning from an existing item
	 * @param clone
	 */
	public Item(Item clone) {
		this.quantity = clone.getQuant();
		this.price = new BigDecimal(clone.getPrice());
		this.name = clone.getName();
	}
	
	public double getPrice () {
		return price.doubleValue();
	}
	
	public int getQuant() {
		return quantity;
	}
	
	public String getName() {
		return name;
	}
	
	public void singleQuantity(){
		quantity = 1;
	}
	
	public void setPrice(double newPrice) {
		price = new BigDecimal(newPrice);
	}
	
	public void addQuantity(int newStock) {
		quantity += newStock;
	}
	
}
