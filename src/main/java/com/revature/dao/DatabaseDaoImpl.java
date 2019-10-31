package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class DatabaseDaoImpl implements DatabaseDao {
	private Logger log = Logger.getRootLogger();

	/**************************************************************************************
	 * UPDATE and INSERT statements to the database *
	 **************************************************************************************/
	@Override
	public boolean saveUserToDatabase(String username, String password, String first_name, String last_name,
			int admin) {
		int result = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO project_0_users(user_id, username, user_password, first_name, last_name, user_admin)"
					+ "VALUES(user_id_seq.NEXTVAL,?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, first_name);
			ps.setString(4, last_name);
			ps.setInt(5, admin);

			result = ps.executeUpdate();
		} catch (SQLException e) {
			printException("saveUserToDatabase", e);
		}
		if (result == 0) {
			log.info("Query failed");
			return false;
		} else {
			log.info("Query completed");
			return true;
		}
	}

	@Override
	public boolean saveAccountToDatabase(String accountType, float accountBalance, int accountStatus, int userId) {
		int result = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO project_0_accounts(account_id, account_type, account_balance, account_status, user_id)"
					+ "VALUES(account_id_seq.NEXTVAL,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, accountType);
			ps.setFloat(2, accountBalance);
			ps.setInt(3, accountStatus);
			ps.setInt(4, userId);

			result = ps.executeUpdate();
		} catch (SQLException e) {
			printException("saveAccountToDatabase", e);
		}
		if (result == 0) {
			log.info("Query failed");
			return false;
		} else {
			log.info("Query completed");
			return true;
		}
	}

	@Override
	public boolean updateBalance(float amount, float balance, int accountId, int userId) {
		float result = balance + amount;
		// Limit the float to x.xx.
		String resultStr = String.format("%.2f", result);
		result = Float.parseFloat(resultStr);
		try (Connection conn = ConnectionUtil.getConnection()) {
			conn.setAutoCommit(false);
			String query = "UPDATE project_0_accounts " + "SET account_balance = ? " + "WHERE account_id = ? "
					+ "AND user_id = ?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setFloat(1, result);
			ps.setInt(2, accountId);
			ps.setInt(3, userId);

			result = ps.executeUpdate();
			if (result < 1) {
				return false;
			}

			query = "INSERT INTO project_0_transactions(transaction_id, transaction_amount, account_id, user_id)"
					+ "VALUES(transaction_id_seq.NEXTVAL,?,?,?)";

			ps = conn.prepareStatement(query);
			ps.setFloat(1, amount);
			ps.setInt(2, accountId);
			ps.setInt(3, userId);

			result = ps.executeUpdate();
			if (result < 1) {
				return false;
			}
			conn.commit();
			conn.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			printException("updateBalance", e);
		}
		return false;
	}

	@Override
	public boolean updateDeleteAccount(int accountId, int userId) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "UPDATE project_0_accounts " + "SET account_status = ? " + "WHERE account_id = ? "
					+ "AND user_id = ?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, 0);
			ps.setInt(2, accountId);
			ps.setInt(3, userId);

			int result = ps.executeUpdate();
			if (result < 1) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			printException("updateDeleteAccount", e);
		}
		return false;
	}

	/**************************************************************************************
	 * SELECT statements to the database *
	 **************************************************************************************/
	@Override
	public boolean checkUser(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_0_users WHERE UPPER(username) = UPPER(?)";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				log.info("User found");
				return true;
			}
		} catch (SQLException e) {
			printException("checkUser", e);
		}
		log.info("User not found");
		return false;
	}

	@Override
	public boolean checkUserAndPassword(String username, String password) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_0_users " + "WHERE UPPER(username) = UPPER(?) AND user_password = ?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				log.info("User found");
				return true;
			}
		} catch (SQLException e) {
			printException("checkUserAndPassword", e);
			e.printStackTrace();
		}
		log.info("User not found");
		return false;
	}

	@Override
	public void pullUserInformation(String username, String password) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_0_users WHERE UPPER(username) = UPPER(?) AND user_password = ?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			UserDao user = UserDao.currentImplementation;
			if (rs.next()) {
				System.out.println(rs.getInt("user_admin"));
				if (0 == rs.getInt("user_admin")) {
					user.accountSet(false, username, password, rs.getString("first_name"), rs.getString("last_name"),
							rs.getInt("user_id"));
				} else if (1 == rs.getInt("user_admin")) {
					user.accountSet(true, username, password, rs.getString("first_name"), rs.getString("last_name"),
							rs.getInt("user_id"));
				}
				log.info("Logging in");
			}
		} catch (SQLException e) {
			printException("pullUserInformation", e);
		}
	}

	@Override
	public boolean getAccountInformation(int userId) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_0_accounts " + "WHERE user_id = ?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			AccountDao accountDao = AccountDao.currentImplementation;
			while (rs.next()) {
				int accountId = rs.getInt("account_id");
				String accountType = rs.getString("account_type");
				float accountBalance = rs.getFloat("account_balance");
				int accountStatus = rs.getInt("account_status");
				accountDao.accountSet(accountId, accountType, accountBalance, accountStatus, userId);
			}

			log.info("Account information found");
			return true;
		} catch (SQLException e) {
			printException("getAccountInformation", e);
		}
		return false;
	}

	@Override
	public boolean displayTransactionInformation(int accountId, int userId) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_0_transactions " + "WHERE account_id = ? " + "AND user_id = ? ";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, accountId);
			ps.setInt(2, userId);

			ResultSet rs = ps.executeQuery();
			TransactionDao transactionDao = TransactionDaoImpl.currentImplementation;
			while (rs.next()) {
				int transactionId = rs.getInt("transaction_id");
				float transactionAmount = rs.getFloat("transaction_amount");
				transactionDao.setTransaction(transactionId, transactionAmount);
			}
			if (rs.next()) {
				log.info("Transaction information found");
			}
			return true;
		} catch (SQLException e) {
			printException("displayTransactionInformation", e);
		}
		return false;
	}

	@Override
	public boolean displayAllUsers() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT USER_ID, USERNAME, FIRST_NAME, LAST_NAME, USER_ADMIN FROM project_0_users";

			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			AdminViewDao adminView = AdminViewDao.currentImplementation;
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String username = rs.getString("username");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String admin = "";
				if (rs.getInt("user_admin") == 1) {
					admin = "admin";
				} else {
					admin = "default";
				}
				adminView.setUserList(userId, username, firstName, lastName, admin);
			}
		} catch (SQLException e) {
			printException("displayAllUsers", e);
		}
		return false;
	}

	@Override
	public boolean displayAllAccounts() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_0_accounts a "
					+ "LEFT JOIN project_0_users u ON (a.user_id = u.user_id)";

			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			AdminViewDao adminView = AdminViewDao.currentImplementation;
			while (rs.next()) {
				int accountId = rs.getInt("account_id");
				String accountType = rs.getString("account_type");
				float accountBalance = rs.getFloat("account_balance");
				String accountStatus = "";
				int statusNum = rs.getInt("account_status");
				if (statusNum == 1) {
					accountStatus = "ACTIVE";
				} else {
					accountStatus = "NOT ACTIVE";
				}
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				adminView.setAccountList(accountId, accountType, accountBalance, accountStatus, firstName + ", " + lastName);
			}
		} catch (SQLException e) {
			printException("displayAllAccounts", e);
		}
		return false;
	}

	@Override
	public boolean displayAllTransactions() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_0_transactions t "
					+ "LEFT JOIN project_0_users u ON (t.user_id = u.user_id)";

			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			AdminViewDao adminView = AdminViewDao.currentImplementation;
			while (rs.next()) {
				int transactionId = rs.getInt("transaction_id");
				float transactionAmount = rs.getFloat("transaction_amount");
				int accountId = rs.getInt("account_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				adminView.setTransactionList(transactionId, transactionAmount, accountId, firstName + ", " + lastName);
			}
		} catch (SQLException e) {
			printException("displayAllTransactions", e);
		}
		return false;
	}

	/**************************************************************************************
	 * Print exceptions *
	 **************************************************************************************/
	private void printException(String method, SQLException e) {
		log.debug("SQLException hit\nIn Method: " + method + "error:\n" + e);
	}
}
