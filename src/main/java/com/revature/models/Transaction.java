package com.revature.models;

public class Transaction {
	private int transactionId;
	private float transactionAmount;

	public Transaction(int transactionId, float transactionAmount) {
		super();
		this.transactionId = transactionId;
		this.transactionAmount = transactionAmount;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(transactionAmount);
		result = prime * result + transactionId;
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
		Transaction other = (Transaction) obj;
		if (Float.floatToIntBits(transactionAmount) != Float.floatToIntBits(other.transactionAmount))
			return false;
		if (transactionId != other.transactionId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "transaction [transactionId=" + transactionId + ", transactionAmount=" + transactionAmount + "]";
	}

}
