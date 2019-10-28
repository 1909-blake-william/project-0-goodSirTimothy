package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface AccountDao {
	AccountDao currentImplementation = new AccountsDaoImpl();
	
	/**
	 * 
	 * @param accountId
	 * @param accountType
	 * @param accountBalance
	 * @param accountStatus
	 * @param userId
	 */
	void accountSet(int accountId, String accountType, float accountBalance, int accountStatus, int userId);
	
	/**
	 * 
	 * @return
	 */
	List<Account> getAccounts();
}
