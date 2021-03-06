package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

import logger.Logger;
/**
 * Creates a text window with a supplied message
 * @author jeanp
 *
 */
public class TextWindow {
	
	private String message;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String user, String message) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextWindow window = new TextWindow(user, message);
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
	public TextWindow(String user, String message) {
		this.message = message;
		//Logger.popup(user, message);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 150);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblMessage = new JLabel(message);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblMessage, BorderLayout.CENTER);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnOk, BorderLayout.SOUTH);
	}

	public static void main(String user, int response) {
		
		String message = "yo";
		
		switch(response) {
		case 400: message = "Invalid request"; break;
		case 401: message = "Invalid credentials"; break;
		case 402: message = "Invalid stock"; break;
		case 501: message = "Not implemented"; break;
		case 403: message = "real bad son"; break;
		}
		
		final String m1 = message;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextWindow window = new TextWindow(user, m1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
