package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.AccountViewAdmin;
import com.revature.models.TransactionViewAdmin;
import com.revature.models.UsersViewAdmin;

public class AdminViewDaoImpl implements AdminViewDao {
	List<UsersViewAdmin> userView = new ArrayList<UsersViewAdmin>();
	List<AccountViewAdmin> accountView = new ArrayList<AccountViewAdmin>();
	List<TransactionViewAdmin> transactionView = new ArrayList<TransactionViewAdmin>();

	@Override
	public void setUserList(int userId, String username, String firstName, String lastname, String admin) {
		userView.add(new UsersViewAdmin(userId, username, firstName, lastname, admin));
		
	}

	@Override
	public void setAccountList(int accountId, String accountType, float accountBalance, String accountStatus, String username) {
		accountView.add(new AccountViewAdmin(accountId, accountType, accountBalance, accountStatus, username));
		
	}

	@Override
	public void setTransactionList(int transactionId, float transactionAmount, int accountId, String username) {
		transactionView.add(new TransactionViewAdmin(transactionId, transactionAmount, accountId, username));
		
	}

	@Override
	public List<UsersViewAdmin> getUserList() {
		return userView;
	}

	@Override
	public List<AccountViewAdmin> getAccountList() {
		return accountView;
	}

	@Override
	public List<TransactionViewAdmin> getTransactionList() {
		return transactionView;
	}

}
