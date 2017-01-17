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

public class Register {

	private JFrame frame;
	
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
	
	private LinkedList<Item> cart = new LinkedList<Item>();
	private Inventory inventory = new Inventory();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	private void updateCart() {
		
		double total = 0;
		txtrCart.setText("");
		
		for (int i = 0; i < cart.size(); i++)
		{
			Item current = cart.get(i);
			
			double subtotal = current.getPrice() * current.getQuant();
			
			total += subtotal;
			
			String item = String.format("%s @ %.2f * %d = %.2f", current.getName(), current.getPrice(), current.getQuant(), subtotal); 
			
			if (txtrCart.getText() == "")
			{
				txtrCart.setText(item + "\n");
			}
			else {
				txtrCart.append(item + "\n");
			}
		}
		
		txtTotal.setText("$"+Double.toString(total));
		
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		btnChips = new JButton("Chips");
		btnChips.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Item temp = inventory.getItem(btnChips.getText());
				temp.singleQuantity();
				
				cart.add(temp);
				
				updateCart();
				
			}
		});
		GridBagConstraints gbc_btnChips = new GridBagConstraints();
		gbc_btnChips.insets = new Insets(0, 0, 5, 5);
		gbc_btnChips.gridx = 0;
		gbc_btnChips.gridy = 0;
		frame.getContentPane().add(btnChips, gbc_btnChips);
		
		btnWater = new JButton("Water");
		btnWater.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Item temp = inventory.getItem(btnWater.getText());
				temp.singleQuantity();
				
				cart.add(temp);
				
				updateCart();
			}
		});
		GridBagConstraints gbc_btnWater = new GridBagConstraints();
		gbc_btnWater.insets = new Insets(0, 0, 5, 5);
		gbc_btnWater.gridx = 2;
		gbc_btnWater.gridy = 0;
		frame.getContentPane().add(btnWater, gbc_btnWater);
		
		btnBakedChips = new JButton("Baked Chips");
		btnBakedChips.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Item temp = inventory.getItem(btnBakedChips.getText());
				temp.singleQuantity();
				
				cart.add(temp);
				
				updateCart();
			}
		});
		GridBagConstraints gbc_btnBakedChips = new GridBagConstraints();
		gbc_btnBakedChips.insets = new Insets(0, 0, 5, 5);
		gbc_btnBakedChips.gridx = 0;
		gbc_btnBakedChips.gridy = 1;
		frame.getContentPane().add(btnBakedChips, gbc_btnBakedChips);
		
		btnCoke = new JButton("Coke");
		btnCoke.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Item temp = inventory.getItem(btnCoke.getText());
				temp.singleQuantity();
				
				cart.add(temp);
				
				updateCart();
			}
		});
		GridBagConstraints gbc_btnCoke = new GridBagConstraints();
		gbc_btnCoke.insets = new Insets(0, 0, 5, 5);
		gbc_btnCoke.gridx = 2;
		gbc_btnCoke.gridy = 1;
		frame.getContentPane().add(btnCoke, gbc_btnCoke);
		
		btnSnickers = new JButton("Snickers");
		btnSnickers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			

				Item temp = inventory.getItem(btnSnickers.getText());
				temp.singleQuantity();
				
				cart.add(temp);
				
				updateCart();
			}
		});
		GridBagConstraints gbc_btnSnickers = new GridBagConstraints();
		gbc_btnSnickers.insets = new Insets(0, 0, 5, 5);
		gbc_btnSnickers.gridx = 0;
		gbc_btnSnickers.gridy = 2;
		frame.getContentPane().add(btnSnickers, gbc_btnSnickers);
		
		btnSprite = new JButton("Sprite");
		btnSprite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Item temp = inventory.getItem(btnSprite.getText());
				temp.singleQuantity();
				
				cart.add(temp);
				
				updateCart();
			}
		});
		GridBagConstraints gbc_btnSprite = new GridBagConstraints();
		gbc_btnSprite.insets = new Insets(0, 0, 5, 5);
		gbc_btnSprite.gridx = 2;
		gbc_btnSprite.gridy = 2;
		frame.getContentPane().add(btnSprite, gbc_btnSprite);
		
		btnPoptarts = new JButton("Pop Tarts");
		btnPoptarts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Item temp = inventory.getItem(btnPoptarts.getText());
				temp.singleQuantity();
				
				cart.add(temp);
				
				updateCart();
			}
		});
		GridBagConstraints gbc_btnPoptarts = new GridBagConstraints();
		gbc_btnPoptarts.insets = new Insets(0, 0, 5, 5);
		gbc_btnPoptarts.gridx = 0;
		gbc_btnPoptarts.gridy = 3;
		frame.getContentPane().add(btnPoptarts, gbc_btnPoptarts);
		
		btnFanta = new JButton("Fanta");
		btnFanta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Item temp = inventory.getItem(btnFanta.getText());
				temp.singleQuantity();
				
				cart.add(temp);
				
				updateCart();
			}
		});
		GridBagConstraints gbc_btnFanta = new GridBagConstraints();
		gbc_btnFanta.insets = new Insets(0, 0, 5, 5);
		gbc_btnFanta.gridx = 2;
		gbc_btnFanta.gridy = 3;
		frame.getContentPane().add(btnFanta, gbc_btnFanta);
		
		btnBurritos = new JButton("Burritos");
		btnBurritos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Item temp = inventory.getItem(btnBurritos.getText());
				temp.singleQuantity();
				
				cart.add(temp);
				
				updateCart();
			}
		});
		GridBagConstraints gbc_btnBurritos = new GridBagConstraints();
		gbc_btnBurritos.insets = new Insets(0, 0, 5, 5);
		gbc_btnBurritos.gridx = 0;
		gbc_btnBurritos.gridy = 4;
		frame.getContentPane().add(btnBurritos, gbc_btnBurritos);
		
		btnCaprisun = new JButton("Capri-Sun");
		btnCaprisun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Item temp = inventory.getItem(btnCaprisun.getText());
				temp.singleQuantity();
				
				cart.add(temp);
				
				updateCart();
			}
		});
		GridBagConstraints gbc_btnCaprisun = new GridBagConstraints();
		gbc_btnCaprisun.insets = new Insets(0, 0, 5, 5);
		gbc_btnCaprisun.gridx = 2;
		gbc_btnCaprisun.gridy = 4;
		frame.getContentPane().add(btnCaprisun, gbc_btnCaprisun);
		
		btnGranolaBars = new JButton("Granola Bars");
		btnGranolaBars.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Item temp = inventory.getItem(btnGranolaBars.getText());
				temp.singleQuantity();
				
				cart.add(temp);
				
				updateCart();
			}
		});
		GridBagConstraints gbc_btnGranolaBars = new GridBagConstraints();
		gbc_btnGranolaBars.insets = new Insets(0, 0, 5, 5);
		gbc_btnGranolaBars.gridx = 0;
		gbc_btnGranolaBars.gridy = 5;
		frame.getContentPane().add(btnGranolaBars, gbc_btnGranolaBars);
		
		btnMonster = new JButton("Monster");
		btnMonster.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Item temp = inventory.getItem(btnMonster.getText());
				temp.singleQuantity();
				
				cart.add(temp);
				
				updateCart();
			}
		});
		GridBagConstraints gbc_btnMonster = new GridBagConstraints();
		gbc_btnMonster.insets = new Insets(0, 0, 5, 5);
		gbc_btnMonster.gridx = 2;
		gbc_btnMonster.gridy = 5;
		frame.getContentPane().add(btnMonster, gbc_btnMonster);
		
		btnCrackers = new JButton("Crackers");
		btnCrackers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Item temp = inventory.getItem(btnCrackers.getText());
				temp.singleQuantity();
				
				cart.add(temp);
				
				updateCart();
			}
		});
		GridBagConstraints gbc_btnCrackers = new GridBagConstraints();
		gbc_btnCrackers.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrackers.gridx = 0;
		gbc_btnCrackers.gridy = 6;
		frame.getContentPane().add(btnCrackers, gbc_btnCrackers);
		
		btnMexicanCoke = new JButton("Mexican Coke");
		btnMexicanCoke.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Item temp = inventory.getItem(btnMexicanCoke.getText());
				temp.singleQuantity();
				
				cart.add(temp);
				
				updateCart();
			}
		});
		GridBagConstraints gbc_btnMexicanCoke = new GridBagConstraints();
		gbc_btnMexicanCoke.insets = new Insets(0, 0, 5, 5);
		gbc_btnMexicanCoke.gridx = 2;
		gbc_btnMexicanCoke.gridy = 6;
		frame.getContentPane().add(btnMexicanCoke, gbc_btnMexicanCoke);
		
		btnRedBull = new JButton("Red Bull");
		btnRedBull.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Item temp = inventory.getItem(btnRedBull.getText());
				temp.singleQuantity();
				
				cart.add(temp);
				
				updateCart();
			}
		});
		GridBagConstraints gbc_btnRedBull = new GridBagConstraints();
		gbc_btnRedBull.insets = new Insets(0, 0, 5, 5);
		gbc_btnRedBull.gridx = 2;
		gbc_btnRedBull.gridy = 7;
		frame.getContentPane().add(btnRedBull, gbc_btnRedBull);
		
		txtTotal = new JTextField();
		txtTotal.setText("0.00");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 10;
		frame.getContentPane().add(txtTotal, gbc_textField);
		txtTotal.setColumns(10);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				cart.clear();
				updateCart();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 11;
		frame.getContentPane().add(btnCancel, gbc_btnCancel);
		
		btnPurchase = new JButton("Purchase");
		btnPurchase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				inventory.updateInventory(cart);
				cart.clear();
				updateCart();
				inventory.updateCSV();
			}
		});
		GridBagConstraints gbc_btnPurchase = new GridBagConstraints();
		gbc_btnPurchase.insets = new Insets(0, 0, 0, 5);
		gbc_btnPurchase.gridx = 2;
		gbc_btnPurchase.gridy = 11;
		frame.getContentPane().add(btnPurchase, gbc_btnPurchase);
		
		txtrCart = new JTextArea();
		txtrCart.setFont(new Font("Monospaced", Font.PLAIN, 10));
		txtrCart.setEditable(false);
		txtrCart.setText("");
		GridBagConstraints gbc_txtrCart = new GridBagConstraints();
		gbc_txtrCart.fill = GridBagConstraints.BOTH;
		gbc_txtrCart.gridx = 3;
		gbc_txtrCart.gridy = 11;
		frame.getContentPane().add(txtrCart, gbc_txtrCart);
	}

}
