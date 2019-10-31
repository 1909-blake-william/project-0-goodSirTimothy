package com.revature.dao;

import java.util.List;

import com.revature.models.AccountViewAdmin;
import com.revature.models.TransactionViewAdmin;
import com.revature.models.UsersViewAdmin;

public interface AdminViewDao {
	
	AdminViewDao currentImplementation = new AdminViewDaoImpl();
	
	/**
	 * Set the user list for displaying all users to admins
	 * @param userId
	 * @param username
	 * @param firstName
	 * @param lastname
	 * @param admin
	 */
	void setUserList(int userId, String username, String firstName, String lastname, String admin);
	
	/**
	 * Set the account list for displaying all accounts to admins
	 * @param accountId
	 * @param accountType
	 * @param accountBalance
	 * @param accountStatus
	 * @param fullName
	 */
	void setAccountList(int accountId, String accountType, float accountBalance, String accountStatus, String fullName);
	
	/**
	 * 
	 * @param transactionId
	 * @param transactionAmount
	 * @param accountId
	 * @param fullName
	 */
	void setTransactionList(int transactionId, float transactionAmount, int accountId, String fullName);
	
	/**
	 * 
	 * @return
	 */
	List<UsersViewAdmin> getUserList();
	
	/**
	 * 
	 * @return
	 */
	List<AccountViewAdmin> getAccountList();
	
	/**
	 * 
	 * @return
	 */
	List<TransactionViewAdmin> getTransactionList();

}
