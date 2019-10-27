package com.revature.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
      
import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class DatabaseDaoImpl implements DatabaseDao{
	private Logger log = Logger.getRootLogger();

	@Override
	public boolean queryToDatabaseStringArray(String query, String[] values) {
		return false;
	}

	@Override
	public boolean queryToDatabaseIntArray(String query, int[] values) {
		return false;
	}
	
	@Override
	public boolean saveUserToDatabase(String username, String password, String first_name, String last_name, int admin) {
		int result = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
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
			log.debug("SQLException hit");
			// e.printStackTrace();
		}
		if(result == 0) {
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
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "INSERT INTO project_0_accounts(account_id, account_type, account_balance, account_status, user_id)"
					+ "VALUES(account_id_seq.NEXTVAL,?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, accountType);
			ps.setFloat(2, accountBalance);
			ps.setInt(3, accountStatus);
			ps.setInt(4, userId);
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			log.debug("SQLException hit");
			// e.printStackTrace();
		}
		if(result == 0) {
			log.info("Query failed");
			return false;
		} else {
			log.info("Query completed");
			return true;
		}
	}
	


	@Override
	public String queryFromDatabase(String query, String[] values) {
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement ps = conn.prepareStatement(query);
			for(int i = 0; i < values.length; i++) {
				ps.setString((i+1), values[i]);
			}
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				for(int i = 0; i < values.length; i++) {
					rs.getString(values[i]);
				}
			}
		} catch (SQLException e) {
			log.debug("SQLException hit");
			//e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkUser(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project_0_users WHERE username = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				log.info("User found");
				return true;
			}
		} catch (SQLException e) {
			log.debug("SQLException hit");
			// e.printStackTrace();
		}
		log.info("User not found");
		return false;
	}

	@Override
	public boolean checkUserAndPassword(String username, String password) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project_0_users "
					+ "WHERE username = ? AND user_password = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				log.info("User found");
				return true;
			}
		} catch (SQLException e) {
			log.debug("SQLException hit");
			// e.printStackTrace();
		}
		log.info("User not found");
		return false;
	}

	@Override
	public void pullUserInformation(String username, String password) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project_0_users "
					+ "WHERE username = ? AND user_password = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			UserDao user = UserDao.currentImplementation;
			if(rs.next()) {
				System.out.println(rs.getInt("user_admin"));
				if(0 == rs.getInt("user_admin")) {
					user.accountSet(false, username, password, rs.getString("first_name"), rs.getString("last_name"), rs.getInt("user_id"));
				} else if(1 == rs.getInt("user_admin")) {
					user.accountSet(true, username, password, rs.getString("first_name"), rs.getString("last_name"), rs.getInt("user_id"));
				}
				log.info("Logging in");
			}
		} catch (SQLException e) {
			log.debug("SQLException hit");
			// e.printStackTrace();
		}
	}

	@Override
	public boolean getAccountInformation(int userId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project_0_accounts "
					+ "WHERE user_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			AccountDao account = AccountDao.currentImplementation;
			while(rs.next()) {
				int accountId = rs.getInt("account_id");
				String accountType = rs.getString("account_type");
				float accountBalance = rs.getInt("account_balance");
				int accountStatus = rs.getInt("account_status");
				account.accountSet(accountId, accountType, accountBalance, accountStatus, userId);
			}

			log.info("Account information found");
			return true;
		} catch (SQLException e) {
			log.debug("SQLException hit");
			// e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateBalance(float amount, float balance, int userId, int accountId) {
		float result = balance + amount;
		try(Connection conn = ConnectionUtil.getConnection()){
			conn.setAutoCommit(false);
			String query = "UPDATE project_0_accounts "
					+ "SET account_balance = ? "
					+ "WHERE account_id = ? "
					+ "AND user_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setFloat(1, balance);
			ps.setInt(2, accountId);
			ps.setInt(3, userId);
			
			result = ps.executeUpdate();
			if(result < 1) {
				return false;
			}
			
			query = "INSERT INTO project_0_transactions(transaction_id, transaction_amount, account_id, user_id)"
					+ "VALUES(transaction_id_seq.NEXTVAL,?,?,?)";
			
			ps = conn.prepareStatement(query);
			ps.setFloat(1, amount);
			ps.setInt(2, accountId);
			ps.setInt(3, userId);
			
			result = ps.executeUpdate();
			if(result < 1) {
				return false;
			}
			conn.commit();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
