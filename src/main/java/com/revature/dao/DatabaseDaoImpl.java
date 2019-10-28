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
	 * 		UPDATE and INSERT statements to the database								  *
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
			} else {
				System.out.println("UPDATE TRUE");
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
			} else {
				System.out.println("INSERT TRUE");
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
			String query = "UPDATE project_0_accounts "
					+ "SET account_status = ? "
					+ "WHERE account_id = ? "
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
	 * 		SELECT statements to the database											  *
	 **************************************************************************************/
	@Override
	public boolean checkUser(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_0_users WHERE username = ?";

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
			String query = "SELECT * FROM project_0_users " + "WHERE username = ? AND user_password = ?";

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
		}
		log.info("User not found");
		return false;
	}

	@Override
	public void pullUserInformation(String username, String password) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_0_users " + "WHERE username = ? AND user_password = ?";

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
			String query = "SELECT * FROM project_0_accounts "
					+ "WHERE user_id = ?";

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
			String query = "SELECT * FROM project_0_transactions "
					+ "WHERE account_id = ? "
					+ "AND user_id = ? ";

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

	/**************************************************************************************
	 * 		Print exceptions															  *
	 **************************************************************************************/
	private void printException(String method, SQLException e) {
		log.debug("SQLException hit\nIn Method: " + method + "error:\n" + e);
	}
}
