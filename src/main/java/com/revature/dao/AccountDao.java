package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface AccountDao {
	AccountDao currentImplementation = new AccountsDaoImpl();
	
	/**
	 * Set values of an account object to a list 
	 * @param accountId, of the account
	 * @param accountType, whether the account is savings, checking, or money market
	 * @param accountBalance, the balance of the account
	 * @param accountStatus, whether the account is active or not
	 * @param userId, the id of the user linked to the account
	 */
	void accountSet(int accountId, String accountType, float accountBalance, int accountStatus, int userId);
	
	/**
	 * return the list of accounts that are stored. 
	 * @return
	 */
	List<Account> getAccounts();
}
