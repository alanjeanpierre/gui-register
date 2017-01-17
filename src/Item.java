
public class Item {

	int quantity;
	double price;
	String name;
	
	public Item(int quantity, double price, String name) {
		this.quantity = quantity;
		this.price = price;
		this.name = name;
	}
	
	public Item(Item clone) {
		this.quantity = clone.getQuant();
		this.price = clone.getPrice();
		this.name = clone.getName();
	}
	
	public double getPrice () {
		return price;
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
		price = newPrice;
	}
	
	public void addQuantity(int newStock) {
		quantity += newStock;
	}
	
}
