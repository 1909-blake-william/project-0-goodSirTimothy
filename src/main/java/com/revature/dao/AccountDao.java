package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface AccountDao {
	AccountDao currentImplementation = new AccountsDaoImpl();
	
	void accountSet(int accountId, String accountType, float accountBalance, int accountStatus, int userId);
	
	void resetArrayList();
	
	List<Account> getAccounts();
}
