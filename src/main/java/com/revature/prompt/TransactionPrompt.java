package com.revature.prompt;

public class TransactionPrompt implements Prompt{

	@Override
	public Prompt run() {
		System.out.println("Transaction history");
		return new UserMenuPrompt();
	}
}
