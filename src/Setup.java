import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Setup {

	private JFrame frame;
	private JTextField txtUsernames;
	private JTextField txtPassword;
	private JTextField txtinventoryfilelocation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Setup window = new Setup();
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
	public Setup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		txtUsernames = new JTextField();
		txtUsernames.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtUsernames.setText("");
			}
		});
		txtUsernames.setText("user,user,user...");
		GridBagConstraints gbc_txtUsernames = new GridBagConstraints();
		gbc_txtUsernames.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsernames.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsernames.gridx = 1;
		gbc_txtUsernames.gridy = 2;
		frame.getContentPane().add(txtUsernames, gbc_txtUsernames);
		txtUsernames.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtPassword.setText("");
			}
		});
		txtPassword.setText("password");
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 1;
		gbc_txtPassword.gridy = 1;
		frame.getContentPane().add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		
		txtinventoryfilelocation = new JTextField();
		txtinventoryfilelocation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtinventoryfilelocation.setText("");
			}
		});
		txtinventoryfilelocation.setText("/inventory/file/location");
		GridBagConstraints gbc_txtinventoryfilelocation = new GridBagConstraints();
		gbc_txtinventoryfilelocation.insets = new Insets(0, 0, 5, 0);
		gbc_txtinventoryfilelocation.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtinventoryfilelocation.gridx = 1;
		gbc_txtinventoryfilelocation.gridy = 3;
		frame.getContentPane().add(txtinventoryfilelocation, gbc_txtinventoryfilelocation);
		txtinventoryfilelocation.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
				    PrintWriter writer = new PrintWriter("setup");

				    writer.println(txtPassword.getText().hashCode());
				    writer.println(txtinventoryfilelocation.getText());
				    
				    Scanner users = new Scanner(txtUsernames.getText());
				    users.useDelimiter(",");
				    
				    writer.print(users.next().hashCode());
				    while (users.hasNext()) {
				    	writer.print("," + users.next().hashCode());
				    }
				    
				    users.close();
				    

				    writer.close();
				} catch (IOException e) {
				    System.err.println("failed to open setup file whyy");
				    System.exit(1);
				}
				
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridx = 1;
		gbc_btnSubmit.gridy = 7;
		frame.getContentPane().add(btnSubmit, gbc_btnSubmit);
	}

}
