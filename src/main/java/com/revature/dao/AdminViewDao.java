package com.revature.dao;

import java.util.List;

import com.revature.models.AccountViewAdmin;
import com.revature.models.TransactionViewAdmin;
import com.revature.models.UsersViewAdmin;

public interface AdminViewDao {
	
	AdminViewDao currentImplementation = new AdminViewDaoImpl();
	
	void setUserList(int userId, String username, String firstName, String lastname, String admin);
	
	void setAccountList(int accountId, String accountType, float accountBalance, String accountStatus, String fullName);
	
	void setTransactionList(int transactionId, float transactionAmount, int accountId, String fullName);
	
	List<UsersViewAdmin> getUserList();
	
	List<AccountViewAdmin> getAccountList();
	
	List<TransactionViewAdmin> getTransactionList();

}
