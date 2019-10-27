package com.revature.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.dao.DatabaseDaoImpl;
import com.revature.prompt.LoginPrompt;
import com.revature.prompt.Prompt;
import com.revature.util.ConnectionUtil;

public class MainMenuDriver {
	private static Logger log = Logger.getRootLogger();
	
	public static void main(String[] args) {
		Prompt p = new LoginPrompt();
		while(true) {
			log.info("" + p.getClass());
			p = p.run();
		}
	}
}
