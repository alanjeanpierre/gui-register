package model;


/**
 * Maintains the password. I'll figure out security later
 * @author Alan Jeanpierre
 *
 */
public class Password {

	String pass;
	
	public Password() {
		// TODO Auto-generated constructor stub
	}
	
	public void loadPassword(String p) {
		pass = p;
	}
	
	public boolean checkPassword(String p) {
		return p.equals(pass);
	}

	public boolean valid(char[] password) {
		// TODO Auto-generated method stub
		return true;
	}

}
