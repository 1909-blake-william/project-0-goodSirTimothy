package com.revature.dao;

import java.sql.PreparedStatement;

import com.revature.models.User;

public interface DatabaseDao {
	
	/**
	 * 
	 * @param query the query to the data base
	 * @param values extra values if needed. 
	 * @return
	 */
	boolean queryToDatabaseStringArray(String query, String[] values);
	
	
	boolean queryToDatabaseIntArray(String query, int[] values);
	
	
	boolean saveUserToDatabase(String username, String password, String first_name, String last_name, int admin);
	
	
	boolean saveAccountToDatabase(String accountType, float accountBalance, int accountStatus, int userId);
	
	/**
	 * get information from the database
	 * @param query
	 * @return
	 */
	String queryFromDatabase(String query, String[] values);
	
	boolean checkUser(String username);
	
	boolean checkUserAndPassword(String username, String password);
	
	void pullUserInformation(String username, String password);
	
	boolean getAccountInformation(int userId);
	
	boolean updateBalance(float amount, float balance, int userId, int accountId);
	
}
