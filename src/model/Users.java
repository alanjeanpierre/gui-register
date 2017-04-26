package model;

import java.util.ArrayList;

/**
 * Class to maintain the list of users. I'll figure out security later
 * @author Alan Jeanpierre
 *
 */
public class Users {
	
	private ArrayList<String> users;
	private ArrayList<Integer> user2;
	
	private String currentUser;

	public Users() {
		// TODO Auto-generated constructor stub
		users = new ArrayList<>();
		user2 = new ArrayList<>();
		currentUser = null;
		
	}

	public void add(int nextInt) {
		// TODO Auto-generated method stub
		
		user2.add(nextInt);
		
	}

	/**
	 * checks if username is in the list of auth'd users
	 * @param username
	 * @param users
	 * @return
	 */
	public boolean checkUser() {
		
		if (currentUser == null) return false;
		System.out.println(currentUser);
		int hashedName = currentUser.hashCode();
		for (int user : user2) {
			
			System.out.println(currentUser.hashCode() + " " + user);
			if (hashedName == user) {
				return true;
			}
		}
		
		return false;
	}

	public void setCurrentUser(String user) {
		// TODO Auto-generated method stub
		
		currentUser = user;
		
	}

	public String getCurrentUser() {
		// TODO Auto-generated method stub
		return currentUser;
	}

}
