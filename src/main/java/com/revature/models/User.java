package com.revature.models;

import java.io.Serializable;

public class User implements Serializable{
	private Boolean admin;
	private String username;
	private String password;
	private String fName;
	private String lName;
	private int accountId;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(Boolean admin, String username, String password, String fName, String lName, int accountId) {
		super();
		this.admin = admin;
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.accountId = accountId;
	}

	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + accountId;
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (accountId != other.accountId)
			return false;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "User [admin=" + admin + ", fName=" + fName + ", lName=" + lName + ", bankId=" + accountId + "]";
	}
	
	
	
	
	
	
}
