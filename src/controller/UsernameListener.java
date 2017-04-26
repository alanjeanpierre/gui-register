package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Users;
import view.RegisterView;

/**
 * Listener for the username to keep up to date on the current user
 * Lots of unimplemented classes so the compiler is happy
 * @author Alan Jeanpierre
 *
 */
public class UsernameListener implements MouseListener, KeyListener{

	private Users users;
	private RegisterView register;
	
	public UsernameListener(Users users) {
		// TODO Auto-generated constructor stub
		this.users = users;
	}

	/**
	 * Adds register reference to ease initialization
	 * @param register
	 */
	public void registerRegister(RegisterView register) {
		this.register = register;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		users.setCurrentUser(register.getUser());
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		register.clearUsername();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
