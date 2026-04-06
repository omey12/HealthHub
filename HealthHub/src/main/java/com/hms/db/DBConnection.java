package com.hms.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection conn;

    public static Connection getConn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Environment variables se lega (Render pe set karna)
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");

            // Fallback for local development
            if (url == null) {
                url = "jdbc:mysql://localhost:3306/doctor_portal";
                user = "root";
                password = "root";
            }

            conn = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
