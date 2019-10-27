package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.DatabaseDaoImpl;
import com.revature.dao.UserDao;

public class UserCreationPrompt implements Prompt{

	private Scanner scan = new Scanner(System.in);
	DatabaseDaoImpl dbDao = new DatabaseDaoImpl();
	
	@Override
	public Prompt run() {
		System.out.println("Please input your username");
		String username = scan.nextLine();
		
		while(dbDao.checkUser(username)) {
			System.out.println("Sorry, that username has already been taken.\nPlease input a different username");
			username = scan.nextLine();
		}

		System.out.println("Please input your password");
		String password = scan.nextLine();
		
		System.out.println("Please input your first name: ");
		String fName = scan.nextLine();
		
		System.out.println("Please input your last name: ");
		String lName = scan.nextLine();
		
		dbDao.saveUserToDatabase(username, password, fName, lName, 0);
		return new LoginPrompt();
	}

}
