package com.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hms.entity.User;

public class UserDAO {

	private Connection conn;

	public UserDAO(Connection conn) {
		this.conn = conn;
	}

	// ✅ REGISTER
	public boolean userRegister(User user) {

    boolean f = false;

    try {

        if(conn == null){
            System.out.println("❌ DB Connection NULL hai");
            return false;
        }

        String sql = "insert into user_details(full_name, email, password) values(?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getFullName());
        pstmt.setString(2, user.getEmail());
        pstmt.setString(3, user.getPassword());

        System.out.println("👉 Executing query...");

        int i = pstmt.executeUpdate();

        System.out.println("👉 Rows affected: " + i);

        if (i == 1) {
            f = true;
            System.out.println("✅ Insert success");
        } else {
            System.out.println("❌ Insert failed");
        }

    } catch (Exception e) {
        System.out.println("🔥 ERROR:");
        e.printStackTrace();
    }

    return f;
}

	// ✅ LOGIN
	public User loginUser(String email, String password) {

		User user = null;

		try {

			String sql = "select * from user_details where email=? and password=?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email.trim());
			pstmt.setString(2, password.trim());

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				user = new User();

				user.setId(rs.getInt("id"));
				user.setFullName(rs.getString("full_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));

				System.out.println("✅ Login success");

			} else {
				System.out.println("❌ Invalid email/password");
			}

		} catch (Exception e) {
			System.out.println("🔥 ERROR IN LOGIN:");
			e.printStackTrace();
		}

		return user;
	}

	
	public boolean checkOldPassword(int userId, String oldPassword) {

		boolean f = false;

		try {

			String sql = "select * from user_details where id=? and password=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, oldPassword.trim());

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	
	public boolean changePassword(int userId, String newPassword) {

		boolean f = false;

		try {

			String sql = "update user_details set password=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword.trim());
			pstmt.setInt(2, userId);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}
}
