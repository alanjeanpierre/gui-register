import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

import java.util.*;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * main cash register application. buttons for items, cart box, etc.
 * checks for user name
 * @author jeanp
 *
 */
public class Register {

	private JFrame frmPartsBinRegister;
	
	private JTextArea txtrCart;
	private JButton btnPurchase;
	private JButton btnCancel;
	private JButton btnRedBull;
	private JButton btnChips;
	private JButton btnWater;
	private JButton btnBakedChips;
	private JButton btnCoke;
	private JButton btnSnickers;
	private JButton btnSprite;
	private JButton btnPoptarts;
	private JButton btnFanta;
	private JButton btnBurritos;
	private JButton btnCaprisun;
	private JButton btnGranolaBars;
	private JButton btnMonster;
	private JButton btnMexicanCoke;
	private JButton btnCrackers;
	private JTextField txtTotal;
	
	//private LinkedList<Item> cart = new LinkedList<Item>();
	private Inventory inventory = new Inventory("inventory.csv");
	private JButton btnAddStock;
	private JTextField txtUsername;
	private Inventory testCart = new Inventory();
	private String users[] = {"alan", "kasai", "ruben", "sam", "patrick", "jessica"};
	
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frmPartsBinRegister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	/**
	 * checks if username is in the list of auth'd users
	 * @param username
	 * @param users
	 * @return
	 */
	private Boolean checkUser(String username, String users[]) {
		
		for (String user : users) {
			if (username.equals(user)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * reprints the cart to the cart window
	 */
	private void updateCart() {
		
		
		txtTotal.setText(String.format("$%.2f",  testCart.getTotal()));
		txtrCart.setText(testCart.print());
		
	}
	
	/**
	 * updates the hovertext on the buttons
	 * hovertext has name, price and quantity remaining (not counting
	 * the amount in the current cart)
	 */
	private void updateHoverText() {
		
		
		JButton test[] = { btnRedBull, btnChips, btnWater, btnBakedChips, 
				btnCoke, btnSnickers, btnSprite, btnPoptarts, btnFanta, 
				btnBurritos, btnCaprisun, btnGranolaBars, btnMonster, 
				btnMexicanCoke, btnCrackers};
		
		for (JButton i : test) {
			Item temp = inventory.getItem(i.getText());
			String text = String.format("<html>%s<br>%.2f each<br>%d remaining</html>", temp.getName(), temp.getPrice(), temp.getQuant());
			i.setToolTipText(text);
		}
	}
	
	/**
	 * adds item to cart based on name
	 * @param name
	 */
	private void addToCart(String name) {
		
		Item temp = inventory.getItem(name);
		temp.singleQuantity();
		
		//cart.add(temp);
		testCart.addItem(temp);
		
		updateCart();
	}
/*

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
		updateHoverText();
		Logger.startup();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPartsBinRegister = new JFrame();
		frmPartsBinRegister.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				updateHoverText();
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				Logger.close();
			}
		});
		frmPartsBinRegister.setTitle("Parts Bin Register");
		frmPartsBinRegister.setBounds(100, 100, 450, 500);
		frmPartsBinRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frmPartsBinRegister.getContentPane().setLayout(gridBagLayout);
		
		btnChips = new JButton("Chips");
		btnChips.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				addToCart(btnChips.getText());
				
			}
		});
		GridBagConstraints gbc_btnChips = new GridBagConstraints();
		gbc_btnChips.insets = new Insets(0, 0, 5, 5);
		gbc_btnChips.gridx = 0;
		gbc_btnChips.gridy = 0;
		frmPartsBinRegister.getContentPane().add(btnChips, gbc_btnChips);
		
		btnWater = new JButton("Water");
		btnWater.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addToCart(btnWater.getText());
			}
		});
		GridBagConstraints gbc_btnWater = new GridBagConstraints();
		gbc_btnWater.insets = new Insets(0, 0, 5, 5);
		gbc_btnWater.gridx = 2;
		gbc_btnWater.gridy = 0;
		frmPartsBinRegister.getContentPane().add(btnWater, gbc_btnWater);
		
		btnBakedChips = new JButton("Baked Chips");
		btnBakedChips.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addToCart(btnBakedChips.getText());
			}
		});
		GridBagConstraints gbc_btnBakedChips = new GridBagConstraints();
		gbc_btnBakedChips.insets = new Insets(0, 0, 5, 5);
		gbc_btnBakedChips.gridx = 0;
		gbc_btnBakedChips.gridy = 1;
		frmPartsBinRegister.getContentPane().add(btnBakedChips, gbc_btnBakedChips);
		
		btnCoke = new JButton("Coke");
		btnCoke.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addToCart(btnCoke.getText());
			}
		});
		GridBagConstraints gbc_btnCoke = new GridBagConstraints();
		gbc_btnCoke.insets = new Insets(0, 0, 5, 5);
		gbc_btnCoke.gridx = 2;
		gbc_btnCoke.gridy = 1;
		frmPartsBinRegister.getContentPane().add(btnCoke, gbc_btnCoke);
		
		btnSnickers = new JButton("Snickers");
		btnSnickers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				addToCart(btnSnickers.getText());
			}
		});
		GridBagConstraints gbc_btnSnickers = new GridBagConstraints();
		gbc_btnSnickers.insets = new Insets(0, 0, 5, 5);
		gbc_btnSnickers.gridx = 0;
		gbc_btnSnickers.gridy = 2;
		frmPartsBinRegister.getContentPane().add(btnSnickers, gbc_btnSnickers);
		
		btnSprite = new JButton("Sprite");
		btnSprite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addToCart(btnSnickers.getText());
			}
		});
		GridBagConstraints gbc_btnSprite = new GridBagConstraints();
		gbc_btnSprite.insets = new Insets(0, 0, 5, 5);
		gbc_btnSprite.gridx = 2;
		gbc_btnSprite.gridy = 2;
		frmPartsBinRegister.getContentPane().add(btnSprite, gbc_btnSprite);
		
		btnPoptarts = new JButton("Pop Tarts");
		btnPoptarts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addToCart(btnPoptarts.getText());
			}
		});
		GridBagConstraints gbc_btnPoptarts = new GridBagConstraints();
		gbc_btnPoptarts.insets = new Insets(0, 0, 5, 5);
		gbc_btnPoptarts.gridx = 0;
		gbc_btnPoptarts.gridy = 3;
		frmPartsBinRegister.getContentPane().add(btnPoptarts, gbc_btnPoptarts);
		
		btnFanta = new JButton("Fanta");
		btnFanta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addToCart(btnFanta.getText());
			}
		});
		GridBagConstraints gbc_btnFanta = new GridBagConstraints();
		gbc_btnFanta.insets = new Insets(0, 0, 5, 5);
		gbc_btnFanta.gridx = 2;
		gbc_btnFanta.gridy = 3;
		frmPartsBinRegister.getContentPane().add(btnFanta, gbc_btnFanta);
		
		btnBurritos = new JButton("Burritos");
		btnBurritos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addToCart(btnBurritos.getText());
			}
		});
		GridBagConstraints gbc_btnBurritos = new GridBagConstraints();
		gbc_btnBurritos.insets = new Insets(0, 0, 5, 5);
		gbc_btnBurritos.gridx = 0;
		gbc_btnBurritos.gridy = 4;
		frmPartsBinRegister.getContentPane().add(btnBurritos, gbc_btnBurritos);
		
		btnCaprisun = new JButton("Capri-Sun");
		btnCaprisun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addToCart(btnCaprisun.getText());
			}
		});
		GridBagConstraints gbc_btnCaprisun = new GridBagConstraints();
		gbc_btnCaprisun.insets = new Insets(0, 0, 5, 5);
		gbc_btnCaprisun.gridx = 2;
		gbc_btnCaprisun.gridy = 4;
		frmPartsBinRegister.getContentPane().add(btnCaprisun, gbc_btnCaprisun);
		
		btnGranolaBars = new JButton("Granola Bars");
		btnGranolaBars.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addToCart(btnGranolaBars.getText());
			}
		});
		GridBagConstraints gbc_btnGranolaBars = new GridBagConstraints();
		gbc_btnGranolaBars.insets = new Insets(0, 0, 5, 5);
		gbc_btnGranolaBars.gridx = 0;
		gbc_btnGranolaBars.gridy = 5;
		frmPartsBinRegister.getContentPane().add(btnGranolaBars, gbc_btnGranolaBars);
		
		btnMonster = new JButton("Monster");
		btnMonster.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addToCart(btnMonster.getText());
			}
		});
		GridBagConstraints gbc_btnMonster = new GridBagConstraints();
		gbc_btnMonster.insets = new Insets(0, 0, 5, 5);
		gbc_btnMonster.gridx = 2;
		gbc_btnMonster.gridy = 5;
		frmPartsBinRegister.getContentPane().add(btnMonster, gbc_btnMonster);
		
		btnCrackers = new JButton("Crackers");
		btnCrackers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addToCart(btnCrackers.getText());
			}
		});
		GridBagConstraints gbc_btnCrackers = new GridBagConstraints();
		gbc_btnCrackers.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrackers.gridx = 0;
		gbc_btnCrackers.gridy = 6;
		frmPartsBinRegister.getContentPane().add(btnCrackers, gbc_btnCrackers);
		
		btnMexicanCoke = new JButton("Mexican Coke");
		btnMexicanCoke.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addToCart(btnMexicanCoke.getText());
			}
		});
		GridBagConstraints gbc_btnMexicanCoke = new GridBagConstraints();
		gbc_btnMexicanCoke.insets = new Insets(0, 0, 5, 5);
		gbc_btnMexicanCoke.gridx = 2;
		gbc_btnMexicanCoke.gridy = 6;
		frmPartsBinRegister.getContentPane().add(btnMexicanCoke, gbc_btnMexicanCoke);
		
		btnRedBull = new JButton("Red Bull");
		btnRedBull.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addToCart(btnRedBull.getText());
			}
		});
		GridBagConstraints gbc_btnRedBull = new GridBagConstraints();
		gbc_btnRedBull.insets = new Insets(0, 0, 5, 5);
		gbc_btnRedBull.gridx = 2;
		gbc_btnRedBull.gridy = 7;
		frmPartsBinRegister.getContentPane().add(btnRedBull, gbc_btnRedBull);
		
		btnAddStock = new JButton("Add Stock");
		btnAddStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (checkUser(txtUsername.getText(), users)) {
					testCart.clear();
					updateCart();
					
					AddStock.main(inventory, txtUsername.getText());
				}
				else {
					TextWindow.main(txtUsername.getText(), "Error: Invalid User");
				}
			}
		});
		GridBagConstraints gbc_btnAddStock = new GridBagConstraints();
		gbc_btnAddStock.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddStock.gridx = 0;
		gbc_btnAddStock.gridy = 9;
		frmPartsBinRegister.getContentPane().add(btnAddStock, gbc_btnAddStock);
		
		txtUsername = new JTextField();
		txtUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtUsername.setText("");
			}
		});
		txtUsername.setText("username");
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsername.gridx = 0;
		gbc_txtUsername.gridy = 10;
		frmPartsBinRegister.getContentPane().add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		txtTotal = new JTextField();
		txtTotal.setText("$0.00");
		GridBagConstraints gbc_txtTotal = new GridBagConstraints();
		gbc_txtTotal.insets = new Insets(0, 0, 5, 0);
		gbc_txtTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTotal.gridx = 3;
		gbc_txtTotal.gridy = 10;
		frmPartsBinRegister.getContentPane().add(txtTotal, gbc_txtTotal);
		txtTotal.setColumns(10);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				testCart.clear();
				updateCart();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 11;
		frmPartsBinRegister.getContentPane().add(btnCancel, gbc_btnCancel);
		
		btnPurchase = new JButton("Purchase");
		btnPurchase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkUser(txtUsername.getText(), users)) {
					
					Item c = inventory.checkCart(testCart);
					
					if (c != null) {
						TextWindow.main(txtUsername.getText(), String.format("Error: not enough stock for %s", c.getName()));
						return;
					}
					
					
					inventory.updateInventory(testCart, txtUsername.getText(), true);
					testCart.clear();
					updateCart();
					inventory.updateCSV();
					updateHoverText();
				}
				else {
					TextWindow.main(txtUsername.getText(), "Error: Invalid User");
				}
				
			}
		});
		GridBagConstraints gbc_btnPurchase = new GridBagConstraints();
		gbc_btnPurchase.insets = new Insets(0, 0, 0, 5);
		gbc_btnPurchase.gridx = 2;
		gbc_btnPurchase.gridy = 11;
		frmPartsBinRegister.getContentPane().add(btnPurchase, gbc_btnPurchase);
		
		txtrCart = new JTextArea();
		txtrCart.setFont(new Font("Monospaced", Font.PLAIN, 10));
		txtrCart.setEditable(false);
		txtrCart.setText("");
		GridBagConstraints gbc_txtrCart = new GridBagConstraints();
		gbc_txtrCart.fill = GridBagConstraints.BOTH;
		gbc_txtrCart.gridx = 3;
		gbc_txtrCart.gridy = 11;
		frmPartsBinRegister.getContentPane().add(txtrCart, gbc_txtrCart);
	}

}
