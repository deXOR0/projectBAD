import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Connect {
	
	public Statement stat;
	public ResultSet rs;
	public ResultSetMetaData rsm;
	public PreparedStatement pStat;
	public Connection con;

	public Connect() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fish_shop", "root", "");
			
			stat = con.createStatement();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ResultSet execQuery(String query) {
		try {
			rs = stat.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void insertIntoUsers(String UserID, String UserFullName, String UserEmail, String UserPhone, String UserPassword, String UserGender, String UserAddress, String UserRole) {
		try {
			pStat = con.prepareStatement("INSERT INTO `users` (`UserID`, `UserFullName`, `UserEmail`, `UserPhone`, `UserPassword`, `UserGender`, `UserAddress`, `UserRole`) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");
			pStat.setString(1, UserID);
			pStat.setString(2, UserFullName);
			pStat.setString(3, UserEmail);
			pStat.setString(4, UserPhone);
			pStat.setString(5, UserPassword);
			pStat.setString(6, UserGender);
			pStat.setString(7, UserAddress);
			pStat.setString(8, UserRole);
			
			pStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			if(stat!=null) {
				stat.close();
				stat=null;
			}
			if(con!=null) {
				con.close();
				con=null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void updateUserData(String UserID, String UserFullName, String UserEmail, String UserPhone, String UserGender, String UserAddress) {
		try {
			pStat = con.prepareStatement("UPDATE `users` SET "
					+ "`UserFullName` = ?,"
					+ "`UserEmail` = ?,"
					+ "`UserPhone` = ?,"
					+ "`UserGender` = ?,"
					+ "`UserAddress` = ? "
					+ "WHERE `users`.`UserID` = ?");
			pStat.setString(1, UserFullName);
			pStat.setString(2, UserEmail);
			pStat.setString(3, UserPhone);
			pStat.setString(4, UserGender);
			pStat.setString(5, UserAddress);
			pStat.setString(6, UserID);
			
			pStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateUserPassword(String UserID, String UserPassword) {
		try {
			pStat = con.prepareStatement("UPDATE `users` SET "
						+ "`UserPassword` = ?"
						+ "WHERE `users`.`UserID` = ?");
			pStat.setString(1, UserPassword);
			pStat.setString(2, UserID);
			
			pStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
