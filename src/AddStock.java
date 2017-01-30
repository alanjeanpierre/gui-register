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
	private JTextField txtChips;
	private JTextField txtBakedChips;
	private JTextField txtSnickers;
	private JTextField txtPopTarts;
	private JTextField txtBurritos;
	private JTextField txtGranolaBars;
	private JTextField txtCrackers;
	private JTextField txtWater;
	private JTextField txtCoke;
	private JTextField txtSprite;
	private JTextField txtFanta;
	private JTextField txtCaprisun;
	private JTextField txtMonster;
	private JTextField txtMexicanCoke;
	private JTextField txtRedBull;
	private JPasswordField pwdPassword;
	
	private Inventory inventory;
	private JTextField chipsPrice;
	private JTextField bakedChipsPrice;
	private JTextField snickersPrice;
	private JTextField poptartsPrice;
	private JTextField burritosPrice;
	private JTextField granolaPrice;
	private JTextField crackersPrice;
	private JTextField waterPrice;
	private JTextField cokePrice;
	private JTextField spritePrice;
	private JTextField fantaPrice;
	private JTextField capriSunPrice;
	private JTextField monsterPrice;
	private JTextField mexicanCokePrice;
	private JTextField redBullPrice;
	private JLabel lblReset;
	private JCheckBox chckbxResetInv;
	
	private String user;

	/**
	 * Launch the application.
	 */
	public static void main(Inventory inventory, String user) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStock window = new AddStock(inventory, user);
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
	public AddStock(Inventory inventory, String user) {
		this.user = user;
		this.inventory = inventory;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 610, 764);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{119, 0, 82, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblItem = new JLabel("Item");
		GridBagConstraints gbc_lblItem = new GridBagConstraints();
		gbc_lblItem.insets = new Insets(0, 0, 5, 5);
		gbc_lblItem.gridx = 0;
		gbc_lblItem.gridy = 2;
		frame.getContentPane().add(lblItem, gbc_lblItem);
		
		JLabel lblPrice = new JLabel("Price");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 1;
		gbc_lblPrice.gridy = 2;
		frame.getContentPane().add(lblPrice, gbc_lblPrice);
		
		JLabel lblAmount = new JLabel("Amount");
		GridBagConstraints gbc_lblAmount = new GridBagConstraints();
		gbc_lblAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmount.gridx = 2;
		gbc_lblAmount.gridy = 2;
		frame.getContentPane().add(lblAmount, gbc_lblAmount);
		
		JLabel lblItem_1 = new JLabel("Item");
		GridBagConstraints gbc_lblItem_1 = new GridBagConstraints();
		gbc_lblItem_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblItem_1.gridx = 3;
		gbc_lblItem_1.gridy = 2;
		frame.getContentPane().add(lblItem_1, gbc_lblItem_1);
		
		JLabel lblPrice_1 = new JLabel("Price");
		GridBagConstraints gbc_lblPrice_1 = new GridBagConstraints();
		gbc_lblPrice_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice_1.gridx = 4;
		gbc_lblPrice_1.gridy = 2;
		frame.getContentPane().add(lblPrice_1, gbc_lblPrice_1);
		
		JLabel lblAmount_1 = new JLabel("Amount");
		GridBagConstraints gbc_lblAmount_1 = new GridBagConstraints();
		gbc_lblAmount_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblAmount_1.gridx = 5;
		gbc_lblAmount_1.gridy = 2;
		frame.getContentPane().add(lblAmount_1, gbc_lblAmount_1);
		
		JLabel lblChips = new JLabel("Chips");
		GridBagConstraints gbc_lblChips = new GridBagConstraints();
		gbc_lblChips.anchor = GridBagConstraints.EAST;
		gbc_lblChips.insets = new Insets(0, 0, 5, 5);
		gbc_lblChips.gridx = 0;
		gbc_lblChips.gridy = 3;
		frame.getContentPane().add(lblChips, gbc_lblChips);
		
		chipsPrice = new JTextField();
		chipsPrice.setText(Double.toString(inventory.getItem("Chips").getPrice()));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		frame.getContentPane().add(chipsPrice, gbc_textField);
		chipsPrice.setColumns(10);
		
		txtChips = new JTextField();
		txtChips.setText("0");
		GridBagConstraints gbc_txtChips = new GridBagConstraints();
		gbc_txtChips.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtChips.insets = new Insets(0, 0, 5, 5);
		gbc_txtChips.gridx = 2;
		gbc_txtChips.gridy = 3;
		frame.getContentPane().add(txtChips, gbc_txtChips);
		txtChips.setColumns(10);
		
		JLabel lblWater = new JLabel("Water");
		GridBagConstraints gbc_lblWater = new GridBagConstraints();
		gbc_lblWater.anchor = GridBagConstraints.EAST;
		gbc_lblWater.insets = new Insets(0, 0, 5, 5);
		gbc_lblWater.gridx = 3;
		gbc_lblWater.gridy = 3;
		frame.getContentPane().add(lblWater, gbc_lblWater);
		
		waterPrice = new JTextField();
		waterPrice.setText(Double.toString(inventory.getItem("Water").getPrice()));
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 4;
		gbc_textField_7.gridy = 3;
		frame.getContentPane().add(waterPrice, gbc_textField_7);
		waterPrice.setColumns(10);
		
		txtWater = new JTextField();
		txtWater.setText("0");
		GridBagConstraints gbc_txtWater = new GridBagConstraints();
		gbc_txtWater.insets = new Insets(0, 0, 5, 0);
		gbc_txtWater.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWater.gridx = 5;
		gbc_txtWater.gridy = 3;
		frame.getContentPane().add(txtWater, gbc_txtWater);
		txtWater.setColumns(10);
		
		JLabel lblBakedChips = new JLabel("Baked Chips");
		GridBagConstraints gbc_lblBakedChips = new GridBagConstraints();
		gbc_lblBakedChips.anchor = GridBagConstraints.EAST;
		gbc_lblBakedChips.insets = new Insets(0, 0, 5, 5);
		gbc_lblBakedChips.gridx = 0;
		gbc_lblBakedChips.gridy = 4;
		frame.getContentPane().add(lblBakedChips, gbc_lblBakedChips);
		
		bakedChipsPrice = new JTextField();
		bakedChipsPrice.setText(Double.toString(inventory.getItem("Baked Chips").getPrice()));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 4;
		frame.getContentPane().add(bakedChipsPrice, gbc_textField_1);
		bakedChipsPrice.setColumns(10);
		
		txtBakedChips = new JTextField();
		txtBakedChips.setText("0");
		GridBagConstraints gbc_txtBakedChips = new GridBagConstraints();
		gbc_txtBakedChips.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBakedChips.insets = new Insets(0, 0, 5, 5);
		gbc_txtBakedChips.gridx = 2;
		gbc_txtBakedChips.gridy = 4;
		frame.getContentPane().add(txtBakedChips, gbc_txtBakedChips);
		txtBakedChips.setColumns(10);
		
		JLabel lblCoke = new JLabel("Coke");
		GridBagConstraints gbc_lblCoke = new GridBagConstraints();
		gbc_lblCoke.anchor = GridBagConstraints.EAST;
		gbc_lblCoke.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoke.gridx = 3;
		gbc_lblCoke.gridy = 4;
		frame.getContentPane().add(lblCoke, gbc_lblCoke);
		
		cokePrice = new JTextField();
		cokePrice.setText(Double.toString(inventory.getItem("Coke").getPrice()));
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 4;
		gbc_textField_8.gridy = 4;
		frame.getContentPane().add(cokePrice, gbc_textField_8);
		cokePrice.setColumns(10);
		
		txtCoke = new JTextField();
		txtCoke.setText("0");
		GridBagConstraints gbc_txtCoke = new GridBagConstraints();
		gbc_txtCoke.insets = new Insets(0, 0, 5, 0);
		gbc_txtCoke.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCoke.gridx = 5;
		gbc_txtCoke.gridy = 4;
		frame.getContentPane().add(txtCoke, gbc_txtCoke);
		txtCoke.setColumns(10);
		
		JLabel lblSnickers = new JLabel("Snickers");
		GridBagConstraints gbc_lblSnickers = new GridBagConstraints();
		gbc_lblSnickers.anchor = GridBagConstraints.EAST;
		gbc_lblSnickers.insets = new Insets(0, 0, 5, 5);
		gbc_lblSnickers.gridx = 0;
		gbc_lblSnickers.gridy = 5;
		frame.getContentPane().add(lblSnickers, gbc_lblSnickers);
		
		snickersPrice = new JTextField();
		snickersPrice.setText(Double.toString(inventory.getItem("Snickers").getPrice()));
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 5;
		frame.getContentPane().add(snickersPrice, gbc_textField_2);
		snickersPrice.setColumns(10);
		
		txtSnickers = new JTextField();
		txtSnickers.setText("0");
		GridBagConstraints gbc_txtSnickers = new GridBagConstraints();
		gbc_txtSnickers.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSnickers.insets = new Insets(0, 0, 5, 5);
		gbc_txtSnickers.gridx = 2;
		gbc_txtSnickers.gridy = 5;
		frame.getContentPane().add(txtSnickers, gbc_txtSnickers);
		txtSnickers.setColumns(10);
		
		JLabel lblSprite = new JLabel("Sprite");
		GridBagConstraints gbc_lblSprite = new GridBagConstraints();
		gbc_lblSprite.anchor = GridBagConstraints.EAST;
		gbc_lblSprite.insets = new Insets(0, 0, 5, 5);
		gbc_lblSprite.gridx = 3;
		gbc_lblSprite.gridy = 5;
		frame.getContentPane().add(lblSprite, gbc_lblSprite);
		
		spritePrice = new JTextField();
		spritePrice.setText(Double.toString(inventory.getItem("Sprite").getPrice()));
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.gridx = 4;
		gbc_textField_9.gridy = 5;
		frame.getContentPane().add(spritePrice, gbc_textField_9);
		spritePrice.setColumns(10);
		
		txtSprite = new JTextField();
		txtSprite.setText("0");
		GridBagConstraints gbc_txtSprite = new GridBagConstraints();
		gbc_txtSprite.insets = new Insets(0, 0, 5, 0);
		gbc_txtSprite.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSprite.gridx = 5;
		gbc_txtSprite.gridy = 5;
		frame.getContentPane().add(txtSprite, gbc_txtSprite);
		txtSprite.setColumns(10);
		
		JLabel lblPopTarts = new JLabel("Pop Tarts");
		GridBagConstraints gbc_lblPopTarts = new GridBagConstraints();
		gbc_lblPopTarts.anchor = GridBagConstraints.EAST;
		gbc_lblPopTarts.insets = new Insets(0, 0, 5, 5);
		gbc_lblPopTarts.gridx = 0;
		gbc_lblPopTarts.gridy = 6;
		frame.getContentPane().add(lblPopTarts, gbc_lblPopTarts);
		
		poptartsPrice = new JTextField();
		poptartsPrice.setText(Double.toString(inventory.getItem("Pop Tarts").getPrice()));
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 6;
		frame.getContentPane().add(poptartsPrice, gbc_textField_3);
		poptartsPrice.setColumns(10);
		
		txtPopTarts = new JTextField();
		txtPopTarts.setText("0");
		GridBagConstraints gbc_txtPopTarts = new GridBagConstraints();
		gbc_txtPopTarts.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPopTarts.insets = new Insets(0, 0, 5, 5);
		gbc_txtPopTarts.gridx = 2;
		gbc_txtPopTarts.gridy = 6;
		frame.getContentPane().add(txtPopTarts, gbc_txtPopTarts);
		txtPopTarts.setColumns(10);
		
		JLabel lblFanta = new JLabel("Fanta");
		GridBagConstraints gbc_lblFanta = new GridBagConstraints();
		gbc_lblFanta.anchor = GridBagConstraints.EAST;
		gbc_lblFanta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFanta.gridx = 3;
		gbc_lblFanta.gridy = 6;
		frame.getContentPane().add(lblFanta, gbc_lblFanta);
		
		fantaPrice = new JTextField();
		fantaPrice.setText(Double.toString(inventory.getItem("Fanta").getPrice()));
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.insets = new Insets(0, 0, 5, 5);
		gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_10.gridx = 4;
		gbc_textField_10.gridy = 6;
		frame.getContentPane().add(fantaPrice, gbc_textField_10);
		fantaPrice.setColumns(10);
		
		txtFanta = new JTextField();
		txtFanta.setText("0");
		GridBagConstraints gbc_txtFanta = new GridBagConstraints();
		gbc_txtFanta.insets = new Insets(0, 0, 5, 0);
		gbc_txtFanta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFanta.gridx = 5;
		gbc_txtFanta.gridy = 6;
		frame.getContentPane().add(txtFanta, gbc_txtFanta);
		txtFanta.setColumns(10);
		
		JLabel lblBurritos = new JLabel("Burritos");
		GridBagConstraints gbc_lblBurritos = new GridBagConstraints();
		gbc_lblBurritos.anchor = GridBagConstraints.EAST;
		gbc_lblBurritos.insets = new Insets(0, 0, 5, 5);
		gbc_lblBurritos.gridx = 0;
		gbc_lblBurritos.gridy = 7;
		frame.getContentPane().add(lblBurritos, gbc_lblBurritos);
		
		burritosPrice = new JTextField();
		burritosPrice.setText(Double.toString(inventory.getItem("Burritos").getPrice()));
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 7;
		frame.getContentPane().add(burritosPrice, gbc_textField_4);
		burritosPrice.setColumns(10);
		
		txtBurritos = new JTextField();
		txtBurritos.setText("0");
		GridBagConstraints gbc_txtBurritos = new GridBagConstraints();
		gbc_txtBurritos.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBurritos.insets = new Insets(0, 0, 5, 5);
		gbc_txtBurritos.gridx = 2;
		gbc_txtBurritos.gridy = 7;
		frame.getContentPane().add(txtBurritos, gbc_txtBurritos);
		txtBurritos.setColumns(10);
		
		JLabel lblCaprisun = new JLabel("Capri-Sun");
		GridBagConstraints gbc_lblCaprisun = new GridBagConstraints();
		gbc_lblCaprisun.anchor = GridBagConstraints.EAST;
		gbc_lblCaprisun.insets = new Insets(0, 0, 5, 5);
		gbc_lblCaprisun.gridx = 3;
		gbc_lblCaprisun.gridy = 7;
		frame.getContentPane().add(lblCaprisun, gbc_lblCaprisun);
		
		capriSunPrice = new JTextField();
		capriSunPrice.setText(Double.toString(inventory.getItem("Capri-Sun").getPrice()));
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.insets = new Insets(0, 0, 5, 5);
		gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_11.gridx = 4;
		gbc_textField_11.gridy = 7;
		frame.getContentPane().add(capriSunPrice, gbc_textField_11);
		capriSunPrice.setColumns(10);
		
		txtCaprisun = new JTextField();
		txtCaprisun.setText("0");
		GridBagConstraints gbc_txtCaprisun = new GridBagConstraints();
		gbc_txtCaprisun.insets = new Insets(0, 0, 5, 0);
		gbc_txtCaprisun.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCaprisun.gridx = 5;
		gbc_txtCaprisun.gridy = 7;
		frame.getContentPane().add(txtCaprisun, gbc_txtCaprisun);
		txtCaprisun.setColumns(10);
		
		JLabel lblGranolaBars = new JLabel("Granola Bars");
		GridBagConstraints gbc_lblGranolaBars = new GridBagConstraints();
		gbc_lblGranolaBars.anchor = GridBagConstraints.EAST;
		gbc_lblGranolaBars.insets = new Insets(0, 0, 5, 5);
		gbc_lblGranolaBars.gridx = 0;
		gbc_lblGranolaBars.gridy = 8;
		frame.getContentPane().add(lblGranolaBars, gbc_lblGranolaBars);
		
		granolaPrice = new JTextField();
		granolaPrice.setText(Double.toString(inventory.getItem("Granola Bars").getPrice()));
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 8;
		frame.getContentPane().add(granolaPrice, gbc_textField_5);
		granolaPrice.setColumns(10);
		
		txtGranolaBars = new JTextField();
		txtGranolaBars.setText("0");
		GridBagConstraints gbc_txtGranolaBars = new GridBagConstraints();
		gbc_txtGranolaBars.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGranolaBars.insets = new Insets(0, 0, 5, 5);
		gbc_txtGranolaBars.gridx = 2;
		gbc_txtGranolaBars.gridy = 8;
		frame.getContentPane().add(txtGranolaBars, gbc_txtGranolaBars);
		txtGranolaBars.setColumns(10);
		
		JLabel lblMonster = new JLabel("Monster");
		GridBagConstraints gbc_lblMonster = new GridBagConstraints();
		gbc_lblMonster.anchor = GridBagConstraints.EAST;
		gbc_lblMonster.insets = new Insets(0, 0, 5, 5);
		gbc_lblMonster.gridx = 3;
		gbc_lblMonster.gridy = 8;
		frame.getContentPane().add(lblMonster, gbc_lblMonster);
		
		monsterPrice = new JTextField();
		monsterPrice.setText(Double.toString(inventory.getItem("Monster").getPrice()));
		GridBagConstraints gbc_textField_12 = new GridBagConstraints();
		gbc_textField_12.insets = new Insets(0, 0, 5, 5);
		gbc_textField_12.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_12.gridx = 4;
		gbc_textField_12.gridy = 8;
		frame.getContentPane().add(monsterPrice, gbc_textField_12);
		monsterPrice.setColumns(10);
		
		txtMonster = new JTextField();
		txtMonster.setText("0");
		GridBagConstraints gbc_txtMonster = new GridBagConstraints();
		gbc_txtMonster.insets = new Insets(0, 0, 5, 0);
		gbc_txtMonster.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMonster.gridx = 5;
		gbc_txtMonster.gridy = 8;
		frame.getContentPane().add(txtMonster, gbc_txtMonster);
		txtMonster.setColumns(10);
		
		JLabel lblCrackers = new JLabel("Crackers");
		GridBagConstraints gbc_lblCrackers = new GridBagConstraints();
		gbc_lblCrackers.anchor = GridBagConstraints.EAST;
		gbc_lblCrackers.insets = new Insets(0, 0, 5, 5);
		gbc_lblCrackers.gridx = 0;
		gbc_lblCrackers.gridy = 9;
		frame.getContentPane().add(lblCrackers, gbc_lblCrackers);
		
		crackersPrice = new JTextField();
		crackersPrice.setText(Double.toString(inventory.getItem("Crackers").getPrice()));
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 9;
		frame.getContentPane().add(crackersPrice, gbc_textField_6);
		crackersPrice.setColumns(10);
		
		txtCrackers = new JTextField();
		txtCrackers.setText("0");
		GridBagConstraints gbc_txtCrackers = new GridBagConstraints();
		gbc_txtCrackers.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCrackers.insets = new Insets(0, 0, 5, 5);
		gbc_txtCrackers.gridx = 2;
		gbc_txtCrackers.gridy = 9;
		frame.getContentPane().add(txtCrackers, gbc_txtCrackers);
		txtCrackers.setColumns(10);
		
		JLabel lblMexicanCoke = new JLabel("Mexican Coke");
		GridBagConstraints gbc_lblMexicanCoke = new GridBagConstraints();
		gbc_lblMexicanCoke.anchor = GridBagConstraints.EAST;
		gbc_lblMexicanCoke.insets = new Insets(0, 0, 5, 5);
		gbc_lblMexicanCoke.gridx = 3;
		gbc_lblMexicanCoke.gridy = 9;
		frame.getContentPane().add(lblMexicanCoke, gbc_lblMexicanCoke);
		
		mexicanCokePrice = new JTextField();
		mexicanCokePrice.setText(Double.toString(inventory.getItem("Mexican Coke").getPrice()));
		GridBagConstraints gbc_textField_13 = new GridBagConstraints();
		gbc_textField_13.insets = new Insets(0, 0, 5, 5);
		gbc_textField_13.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_13.gridx = 4;
		gbc_textField_13.gridy = 9;
		frame.getContentPane().add(mexicanCokePrice, gbc_textField_13);
		mexicanCokePrice.setColumns(10);
		
		txtMexicanCoke = new JTextField();
		txtMexicanCoke.setText("0");
		GridBagConstraints gbc_txtMexicanCoke = new GridBagConstraints();
		gbc_txtMexicanCoke.insets = new Insets(0, 0, 5, 0);
		gbc_txtMexicanCoke.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMexicanCoke.gridx = 5;
		gbc_txtMexicanCoke.gridy = 9;
		frame.getContentPane().add(txtMexicanCoke, gbc_txtMexicanCoke);
		txtMexicanCoke.setColumns(10);
		
		JLabel lblRedBull = new JLabel("Red Bull");
		GridBagConstraints gbc_lblRedBull = new GridBagConstraints();
		gbc_lblRedBull.anchor = GridBagConstraints.EAST;
		gbc_lblRedBull.insets = new Insets(0, 0, 5, 5);
		gbc_lblRedBull.gridx = 3;
		gbc_lblRedBull.gridy = 10;
		frame.getContentPane().add(lblRedBull, gbc_lblRedBull);
		
		redBullPrice = new JTextField();
		redBullPrice.setText(Double.toString(inventory.getItem("Red Bull").getPrice()));
		GridBagConstraints gbc_textField_14 = new GridBagConstraints();
		gbc_textField_14.insets = new Insets(0, 0, 5, 5);
		gbc_textField_14.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_14.gridx = 4;
		gbc_textField_14.gridy = 10;
		frame.getContentPane().add(redBullPrice, gbc_textField_14);
		redBullPrice.setColumns(10);
		
		txtRedBull = new JTextField();
		txtRedBull.setText("0");
		GridBagConstraints gbc_txtRedBull = new GridBagConstraints();
		gbc_txtRedBull.insets = new Insets(0, 0, 5, 0);
		gbc_txtRedBull.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRedBull.gridx = 5;
		gbc_txtRedBull.gridy = 10;
		frame.getContentPane().add(txtRedBull, gbc_txtRedBull);
		txtRedBull.setColumns(10);
		
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
				
				char p[] = "partsbin".toCharArray();
				
				if (Arrays.equals(p, pwdPassword.getPassword())) {
					
					LinkedList<Item> newStock = new LinkedList<Item>();
					Inventory newStock2 = new Inventory();
					
					String names[] = {
							"Chips", "Baked Chips", "Snickers", "Pop Tarts",
							"Burritos", "Granola Bars", "Crackers", "Water", 
							"Coke", "Sprite", "Fanta", "Capri-Sun", "Monster", 
							"Mexican Coke", "Red Bull"
					};
					
					JTextField prices[] = {
							chipsPrice, bakedChipsPrice, snickersPrice,
							poptartsPrice, burritosPrice, granolaPrice,
							crackersPrice, waterPrice, cokePrice, spritePrice, 
							fantaPrice, capriSunPrice, monsterPrice, 
							mexicanCokePrice, redBullPrice
					};
					JTextField quants[] = {
							txtChips, txtBakedChips, txtSnickers, txtPopTarts,
							txtBurritos, txtGranolaBars, txtCrackers, txtWater,
							txtCoke, txtSprite, txtFanta, txtCaprisun,
							txtMonster, txtMexicanCoke, txtRedBull
					};

					for (int i = 0; i < quants.length; i++) {
						
						int quant;
						
						if (chckbxResetInv.isSelected()) {
							quant = Integer.parseInt(quants[i].getText());
						}
						else {
							quant = -Integer.parseInt(quants[i].getText());
						}
						double price = Double.parseDouble(prices[i].getText());
						newStock2.addItem(new Item(quant, price, names[i]));
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
