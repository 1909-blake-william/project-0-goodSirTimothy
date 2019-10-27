package com.revature.prompt;

public class AdminViewAccountsPrompt implements Prompt{

	@Override
	public Prompt run() {
		System.out.println("All banking accounts: ");
		return new UserMenuPrompt();
	}
}
