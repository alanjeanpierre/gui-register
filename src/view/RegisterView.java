package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.AddStockController;
import controller.ButtonController;
import controller.PurchaseController;
import controller.UsernameListener;
import logger.Logger;
import model.AbstractInventory;
import model.Cart;
import model.Inventory;
import model.Item;
import model.Users;

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
import java.io.File;

/**
 * main cash register application. buttons for items, cart box, etc.
 * checks for user name
 * @author jeanp
 *
 */
public class RegisterView {

	private JFrame frmPartsBinRegister;
	
	private JTextArea txtrCart;
	private JButton btnPurchase;
	private JButton btnCancel;
	private JTextField txtTotal;
	
	private JButton btnItems[];
	private GridBagConstraints buttonConstraints[];


	private Inventory inventory;
	private Cart testCart;
	private Users users;
	
	
	private JButton btnAddStock;
	private JTextField txtUsername;
	//private ArrayList<Integer> users;
	private int password;
	private String inventoryFile;

	

	/**
	 * reprints the cart to the cart window
	 */
	public void updateCart() {
		
		
		txtTotal.setText(String.format("$%.2f",  testCart.getTotal()));
		txtrCart.setText(testCart.print());
		
	}
	
	/**
	 * updates the hovertext on the buttons
	 * hovertext has name, price and quantity remaining (not counting
	 * the amount in the current cart)
	 */
	public void updateHoverText() {
		
		for (JButton i : btnItems) {
			Item temp = inventory.getItem(i.getText());
			String text = String.format("<html>%s<br>%.2f each<br>%d remaining</html>", temp.getName(), temp.getPrice(), temp.getQuant());
			i.setToolTipText(text);
		}
	}
	

	/**
	 * Create the application.
	 */
	public RegisterView(Inventory inventory, Cart cart, Users users) {
		
		this.inventory = inventory;
		this.testCart = cart;
		this.users = users;
		
		
		
		initialize();
		updateHoverText();
		frmPartsBinRegister.setVisible(true);
	}
	
	/**
	 * Register listeners and reverse register the register
	 * @param b
	 * @param s
	 * @param purchase
	 * @param ulist
	 */
	public void registerListeners(ButtonController b, AddStockController s, PurchaseController purchase, UsernameListener ulist) {
		btnAddStock.addActionListener(s);
		s.registerRegister(this);
		for (JButton j : btnItems) {
			j.addActionListener(b);
			b.registerRegister(this);
		}
		
		btnPurchase.addActionListener(purchase);
		btnCancel.addActionListener(purchase);
		purchase.registerRegister(this);
		
		txtUsername.addKeyListener(ulist);
		txtUsername.addMouseListener(ulist);
		ulist.registerRegister(this);
	}

	/**
	 * Initialize the contents of the frame.
	 * I'll make it nicer later
	 */
	private void initialize() {
		
		int num = inventory.getNumOfItems();
		
		
		frmPartsBinRegister = new JFrame();
		frmPartsBinRegister.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				updateHoverText();
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				Logger.close();
				System.exit(0);
			}
		});
		frmPartsBinRegister.setTitle("Parts Bin Register");
		frmPartsBinRegister.setBounds(100, 100, 75*num/4 + 200, 600);
		frmPartsBinRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0};
		gridBagLayout.columnWeights = new double[2*num/7 + 1];
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frmPartsBinRegister.getContentPane().setLayout(gridBagLayout);
		
		
		btnItems = new JButton[num];		
		for (int i = 0; i < num; i++) {
			btnItems[i] = new JButton(inventory.getItemName(i));
		}

		int x = 0, y = 0, i = 0;;
		buttonConstraints = new GridBagConstraints[inventory.getNumOfItems()];
		for (GridBagConstraints gbc : buttonConstraints) {

			if (y > 7) {
				y = 0;
				x += 2;
			}
			gbc = new GridBagConstraints();
			gbc.insets = new Insets(0,0,5,5);
			gbc.gridx = x;
			gbc.gridy = y;
			frmPartsBinRegister.getContentPane().add(btnItems[i], gbc);
			
			y++;
			i++;
		}
		
		
		
		
		
		btnAddStock = new JButton("Add Stock");
		GridBagConstraints gbc_btnAddStock = new GridBagConstraints();
		gbc_btnAddStock.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddStock.gridx = 0;
		gbc_btnAddStock.gridy = 9;
		frmPartsBinRegister.getContentPane().add(btnAddStock, gbc_btnAddStock);
		
		txtUsername = new JTextField();
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
		gbc_txtTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTotal.insets = new Insets(0, 0, 5, 0);
		gbc_txtTotal.gridx = x + 1;
		gbc_txtTotal.gridy = 10;
		frmPartsBinRegister.getContentPane().add(txtTotal, gbc_txtTotal);
		txtTotal.setColumns(10);
		
		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 11;
		frmPartsBinRegister.getContentPane().add(btnCancel, gbc_btnCancel);
		
		btnPurchase = new JButton("Purchase");
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
		gbc_txtrCart.gridx = x+1;
		gbc_txtrCart.gridy = 11;
		gridBagLayout.columnWeights[x+1] = 1.0;
		frmPartsBinRegister.getContentPane().add(txtrCart, gbc_txtrCart);
	}

	/**
	 * gets username from username box
	 * @return
	 */
	public String getUser() {
		return txtUsername.getText();
	}

	/**
	 * Clears username (used on click)
	 */
	public void clearUsername() {
		// TODO Auto-generated method stub
		txtUsername.setText("");
		
	}

}
