package com.revature.dao;

public interface UserDao {
	UserDao currentImplementation = new UserDaoImpl();
	
	/**
	 * Set the DAO to focus the user object and remember their information. 
	 * @param admin pass in the admin value
	 * @param username pass in the username
	 * @param password pass in the password
	 * @param fName pass in their first name
	 * @param lName pass in their last name
	 * @param accountId pass in the account ID of te user
	 */
	void accountSet(boolean admin, String username, String password, String fName, String lName, int accountId);
	
	/**
	 * @return The first name and last name of the user.
	 */
	String getName();
	
	/**
	 * @return The account ID
	 */
	int getAccountId();
	
	/**
	 * @return the users first name
	 */
	String getfName();
	
	/**
	 * @return the users last name
	 */
	String getlName();
	
	/**
	 * @return get the users username
	 */
	String getUsername();
	
	/**
	 * @return get their admin status. 
	 */
	boolean getAdmin();

}
