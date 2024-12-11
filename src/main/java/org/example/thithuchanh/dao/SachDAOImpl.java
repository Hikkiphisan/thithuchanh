package org.example.thithuchanh.dao;

import org.example.thithuchanh.model.Sach;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SachDAOImpl implements SachDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/quanlythuvien";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hikkiroku";

    public List<Sach> selectAllSach() {
        List<Sach> sachList = new ArrayList<>();
        String query = "SELECT MaSach, TenSach, TacGia, SoLuong FROM Sach";

        try {
            // Đăng ký driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Sach sach = new Sach();
                    sach.setMaSach(rs.getInt("MaSach"));
                    sach.setTenSach(rs.getString("TenSach"));
                    sach.setTacGia(rs.getString("TacGia"));
                    sach.setSoLuong(rs.getInt("SoLuong"));
                    sachList.add(sach);
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sachList;
    }
}