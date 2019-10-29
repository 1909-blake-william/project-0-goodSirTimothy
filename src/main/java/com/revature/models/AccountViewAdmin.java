package com.revature.models;

public class AccountViewAdmin {
	private int accountId;
	private String accountType;
	private float accountBalance;
	private String accountStatus;
	private String fullName;

	public AccountViewAdmin(int accountId, String accountType, float accountBalance, String accountStatus, String fullName) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.setAccountStatus(accountStatus);
		this.fullName = fullName;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public float getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getUsername() {
		return fullName;
	}

	public void setUsername(String username) {
		this.fullName = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(accountBalance);
		result = prime * result + accountId;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
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
		AccountViewAdmin other = (AccountViewAdmin) obj;
		if (Float.floatToIntBits(accountBalance) != Float.floatToIntBits(other.accountBalance))
			return false;
		if (accountId != other.accountId)
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccountViewAdmin [accountId=" + accountId + ", accountType=" + accountType + ", accountBalance="
				+ accountBalance + ", username=" + fullName + "]";
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

}
