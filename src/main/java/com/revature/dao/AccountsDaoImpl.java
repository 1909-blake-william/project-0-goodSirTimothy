package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;

public class AccountsDaoImpl implements AccountDao{
	
	List<Account> accounts = new ArrayList<Account>();

	@Override
	public void accountSet(int accountId, String accountType, float accountBalance, int accountStatus, int userId) {
		accounts.add(new Account(accountId, accountType, accountBalance, accountStatus, userId));
		
	}

	@Override
	public List<Account> getAccounts() {
		return accounts;
	}
}
