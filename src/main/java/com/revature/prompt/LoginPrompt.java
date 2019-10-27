package com.revature.prompt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.dao.DatabaseDaoImpl;
import com.revature.util.ConnectionUtil;

public class LoginPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);

	public Prompt run() {
		System.out.print("Enter 1 to login.\nEnter 2 to make an account: ");
		String input = scan.nextLine();
		switch (input) {
		case "1":

			System.out.println("Please enter your username");
			String username = scan.nextLine();

			System.out.println("Please enter your password");
			String password = scan.nextLine();
			
			try (Connection c = ConnectionUtil.getConnection()) {
				DatabaseDaoImpl dbDao = new DatabaseDaoImpl();

				if (dbDao.checkUserAndPassword(username, password)) {
					dbDao.pullUserInformation(username, password);
					return new UserMenuPrompt();
				}
				System.out.println("\tSorry incorrect credentials\n");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "2":
			return new UserCreationPrompt();

		default:
			break;
		}
		return new LoginPrompt();
	}

}
