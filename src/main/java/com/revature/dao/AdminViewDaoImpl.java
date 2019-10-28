package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.AccountViewAdmin;
import com.revature.models.TransactionViewAdmin;
import com.revature.models.UsersViewAdmin;

public class AdminViewDaoImpl implements AdminViewDao {
	List<UsersViewAdmin> userView = new ArrayList<UsersViewAdmin>();
	List<AccountViewAdmin> accountView = new ArrayList<AccountViewAdmin>();

	@Override
	public void setUserList(int userId, String username, String firstName, String lastname, String admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAccountList(int accountId, String accountType, float accountBalance, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTransactionList(int transactionId, float transactionAmount, int accountId, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UsersViewAdmin> getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountViewAdmin> getAccountList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionViewAdmin> getTransactionList() {
		// TODO Auto-generated method stub
		return null;
	}

}
