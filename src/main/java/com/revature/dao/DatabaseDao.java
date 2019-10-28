package com.revature.dao;

public interface DatabaseDao {

	/**************************************************************************************
	 * 		UPDATE and INSERT statements to the database								  *
	 **************************************************************************************/
	/**
	 * Insert the users values into the database
	 * @param username the username
	 * @param password their password
	 * @param first_name the users first name
	 * @param last_name the users last name
	 * @param admin if they are an admin or not
	 * @return
	 */
	boolean saveUserToDatabase(String username, String password, String first_name, String last_name, int admin);
	
	/**
	 * Insert an account linked to the user into the database.
	 * @param accountType The account type can be Savings, Checking, or Money Market. 
	 * @param accountBalance The balance of the account
	 * @param accountStatus the status of the account (whether or not it is active)
	 * @param userId their user ID so the account it linked to the user
	 * @return
	 */
	boolean saveAccountToDatabase(String accountType, float accountBalance, int accountStatus, int userId);
	
	/**
	 * Update the balance of the user
	 * @param amount the amount for being updated
	 * @param balance the original balance
	 * @param accountId the accounts ID
	 * @param userId the users ID
	 * @return
	 */
	boolean updateBalance(float amount, float balance, int accountId, int userId);
	
	/**
	 * "delete" the account. Set the account active status to 0 to show that it is nolonger in use. 
	 * @param accountId is the accounts ID
	 * @param userId is the users ID
	 * @return
	 */
	boolean updateDeleteAccount(int accountId, int userId);

	/**************************************************************************************
	 * 		SELECT statements to the database											  *
	 **************************************************************************************/
	/**
	 * Check to see if the username is already taken
	 * @param username the username that was entered
	 * @return
	 */
	boolean checkUser(String username);
	
	/**
	 * Check if the username and password match one that is already in the database
	 * @param username entered username
	 * @param password entered password
	 * @return
	 */
	boolean checkUserAndPassword(String username, String password);
	
	/**
	 * Pull the user information from the database and save it to the userDao
	 * @param username the username
	 * @param password the password
	 */
	void pullUserInformation(String username, String password);
	
	/**
	 * this is for getting the different accounts and their information (depending on which user)
	 * @param userId the users user ID.
	 * @return
	 */
	boolean getAccountInformation(int userId);
	
	/**
	 * 
	 * @param accountId
	 * @param userId
	 * @return
	 */
	boolean displayTransactionInformation(int accountId, int userId);
	
	/**
	 * 
	 * @return
	 */
	boolean displayAllUsers();
	
	/**
	 * 
	 * @return
	 */
	boolean displayAllAccounts();
	
	/**
	 * 
	 * @return
	 */
	boolean displayAllTransactions();
	
}
