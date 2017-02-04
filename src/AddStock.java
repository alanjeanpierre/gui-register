import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;

public class AddStock {

	private JFrame frame;
	private JPasswordField pwdPassword;
	
	private Inventory inventory;
	
	private JLabel lblReset;
	private JCheckBox chckbxResetInv;
	
	private JTextField prices[];
	private JTextField quantities[];
	private JLabel names[];
	private GridBagConstraints priceConstraints[];
	private GridBagConstraints nameConstraints[];
	private GridBagConstraints quantConstraints[];
	
	private String user;
	private int password;

	/**
	 * Launch the application.
	 */
	public static void main(Inventory inventory, String user, int password) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStock window = new AddStock(inventory, user, password);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddStock(Inventory inventory, String user, int password) {
		this.password = password;
		this.user = user;
		this.inventory = inventory;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		

		int num = inventory.getNumOfItems();
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, num*30, num*6);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[4*num/6 + 1];
		gridBagLayout.rowHeights = new int[4*num/6 + 1];
		gridBagLayout.columnWeights = new double[4*num/6 + 1];
		gridBagLayout.rowWeights = new double[4*num/6 + 1];
		frame.getContentPane().setLayout(gridBagLayout);
		
			
		prices = new JTextField[num];		
		quantities = new JTextField[num];
		names = new JLabel[num];
		
		for (int i = 0; i < num; i++) {
			String itemName = inventory.getItemName(i);
			prices[i] = new JTextField(Double.toString(inventory.getItem(itemName).getPrice()));
			quantities[i] = new JTextField("0");
			names[i] = new JLabel(itemName);
			
		}

		int x = 0, y = 0, i = 0;
		nameConstraints = new GridBagConstraints[num];
		priceConstraints = new GridBagConstraints[num];
		quantConstraints = new GridBagConstraints[num];
		
		for (i = 0; i < num; i++) {
			
			if (y > 7) {

				gridBagLayout.columnWeights[x] = 1.0;

				gridBagLayout.columnWeights[x+1] = 1.0;

				gridBagLayout.columnWeights[x + 2] = 1.0;
				x += 4;
				y = 0;
			}
			

			nameConstraints[i] = new GridBagConstraints();
			quantConstraints[i] = new GridBagConstraints();
			priceConstraints[i] = new GridBagConstraints();

			nameConstraints[i].insets = new Insets(0,0,5,5);
			quantConstraints[i].insets = new Insets(0,0,5,5);
			priceConstraints[i].insets = new Insets(0,0,5,5);
			
			nameConstraints[i].gridx = x;
			priceConstraints[i].gridx = x + 1;
			quantConstraints[i].gridx = x + 2;
			
			nameConstraints[i].gridy = y;
			priceConstraints[i].gridy = y;
			quantConstraints[i].gridy = y;
			
			priceConstraints[i].fill = GridBagConstraints.HORIZONTAL;
			quantConstraints[i].fill = GridBagConstraints.HORIZONTAL;
			
			frame.getContentPane().add(prices[i], priceConstraints[i]);
			frame.getContentPane().add(quantities[i], quantConstraints[i]);
			frame.getContentPane().add(names[i], nameConstraints[i]);
			
			y++;
			
			
		}
		
		gridBagLayout.columnWeights[x] = 1.0;

		gridBagLayout.columnWeights[x+1] = 1.0;

		gridBagLayout.columnWeights[x + 2] = 1.0;
		
		
		lblReset = new JLabel("Reset");
		GridBagConstraints gbc_lblReset = new GridBagConstraints();
		gbc_lblReset.insets = new Insets(0, 0, 5, 5);
		gbc_lblReset.gridx = 0;
		gbc_lblReset.gridy = 20;
		frame.getContentPane().add(lblReset, gbc_lblReset);
		
		chckbxResetInv = new JCheckBox("");
		GridBagConstraints gbc_chckbxResetInv = new GridBagConstraints();
		gbc_chckbxResetInv.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxResetInv.gridx = 1;
		gbc_chckbxResetInv.gridy = 20;
		frame.getContentPane().add(chckbxResetInv, gbc_chckbxResetInv);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 3;
		gbc_lblPassword.gridy = 20;
		frame.getContentPane().add(lblPassword, gbc_lblPassword);
		
		pwdPassword = new JPasswordField();
		GridBagConstraints gbc_pwdPassword = new GridBagConstraints();
		gbc_pwdPassword.insets = new Insets(0, 0, 5, 0);
		gbc_pwdPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdPassword.gridx = 5;
		gbc_pwdPassword.gridy = 20;
		frame.getContentPane().add(pwdPassword, gbc_pwdPassword);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 21;
		frame.getContentPane().add(btnCancel, gbc_btnCancel);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				int inPwd = pwdPassword.getPassword().toString().hashCode();
				
				if (inPwd == password) {
					
					Inventory newStock2 = new Inventory();
					
					

					for (int i = 0; i < quantities.length; i++) {
						
						int quant;
						
						if (chckbxResetInv.isSelected()) {
							quant = Integer.parseInt(quantities[i].getText());
						}
						else {
							quant = -Integer.parseInt(quantities[i].getText());
						}
						double price = Double.parseDouble(prices[i].getText());
						newStock2.addItem(new Item(quant, price, names[i].getText()));
					}

					
						
					if (chckbxResetInv.isSelected()) {
						inventory.setInventory(newStock2, user);
					}
					else {
						inventory.updateInventory(newStock2, user, false);
					}
						
					inventory.updateCSV();
					
					
					frame.dispose();
				}
				else {
					TextWindow.main(user, "Invalid password");
				}
				
				
			}
		});
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 0, 5);
		gbc_btnSubmit.gridx = 3;
		gbc_btnSubmit.gridy = 21;
		frame.getContentPane().add(btnSubmit, gbc_btnSubmit);
	}

}
