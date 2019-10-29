package com.revature.models;

public class TransactionViewAdmin {
	private int transactionId;
	private float transactionAmount;
	private int accountId;
	private String fullName;

	public TransactionViewAdmin(int transactionId, float transactionAmount, int accountId, String fullName) {
		super();
		this.transactionId = transactionId;
		this.transactionAmount = transactionAmount;
		this.accountId = accountId;
		this.fullName = fullName;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public float getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(float transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
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
		result = prime * result + accountId;
		result = prime * result + Float.floatToIntBits(transactionAmount);
		result = prime * result + transactionId;
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
		TransactionViewAdmin other = (TransactionViewAdmin) obj;
		if (accountId != other.accountId)
			return false;
		if (Float.floatToIntBits(transactionAmount) != Float.floatToIntBits(other.transactionAmount))
			return false;
		if (transactionId != other.transactionId)
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
		return "TransactionViewAdmin [transactionId=" + transactionId + ", transactionAmount=" + transactionAmount
				+ ", accountId=" + accountId + ", username=" + fullName + "]";
	}

}
