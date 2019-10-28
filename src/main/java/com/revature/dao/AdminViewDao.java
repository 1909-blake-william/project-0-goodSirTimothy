package com.revature.dao;

import java.util.List;

import com.revature.models.AccountViewAdmin;
import com.revature.models.TransactionViewAdmin;
import com.revature.models.UsersViewAdmin;

public interface AdminViewDao {
	
	void setUserList(int userId, String username, String firstName, String lastname, String admin);
	
	void setAccountList(int accountId, String accountType, float accountBalance, String username);
	
	void setTransactionList(int transactionId, float transactionAmount, int accountId, String username);
	
	List<UsersViewAdmin> getUserList();
	
	List<AccountViewAdmin> getAccountList();
	
	List<TransactionViewAdmin> getTransactionList();

}
