package com.revature.dao;

import com.revature.models.User;

public class UserDaoImpl implements UserDao{

	User user = new User();


	@Override
	public void accountSet(boolean admin, String username, String password, String fName, String lName, int accountId) {
		user = new User(admin, username, password, fName, lName, accountId);
	}

	@Override
	public String getName() {
		return user.getfName() + ", " + user.getlName();
	}

	@Override
	public int getAccountId() {
		return user.getAccountId();
	}

	@Override
	public String getfName() {
		return user.getfName();
	}

	@Override
	public String getlName() {
		return user.getlName();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean getAdmin() {
		return user.getAdmin();
	}
}
