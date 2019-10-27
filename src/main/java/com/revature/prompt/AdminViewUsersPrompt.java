package com.revature.prompt;

public class AdminViewUsersPrompt implements Prompt{

	@Override
	public Prompt run() {
		System.out.println("All user on server: ");
		return new UserMenuPrompt();
	}
}
