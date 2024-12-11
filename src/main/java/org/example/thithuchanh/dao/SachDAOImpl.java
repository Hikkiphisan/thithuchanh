package org.example.thithuchanh.dao;

import org.example.thithuchanh.model.HocSinh;
import org.example.thithuchanh.model.Sach;
import org.example.thithuchanh.model.TheMuonSach;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SachDAOImpl implements SachDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/quanlythuvien";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hikkiroku";


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    public List<Sach> selectAllSach() {
        List<Sach> sachList = new ArrayList<>();
        String query = "SELECT MaSach, TenSach, TacGia, SoLuong FROM Sach";



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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return sachList;
    }

    public void decreaseBookStock_truMotTrongSQL(String masach) {
        String updateQuery = "UPDATE sach SET soluong = soluong - 1 WHERE masach = ? AND soluong > 0";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(updateQuery)) {

            ps.setString(1, masach);
            ps.executeUpdate();  // Giảm số lượng sách trong kho
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public boolean inserttoTableTheMuonSach(String mamuonsach, String mahocsinh, String masach, String ngaymuon, String ngaytra, String trangthai) {
        String insertQuery = "INSERT INTO borrowed_books (mamuonsach, mahocsinh, masach, ngaymuon, ngaytra, trangthai) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(insertQuery)) {

            ps.setString(1, mamuonsach);
            ps.setString(2, mahocsinh);
            ps.setString(3, masach);
            ps.setString(4, ngaymuon);
            ps.setString(5, ngaytra);
            ps.setString(6, trangthai);

            int result = ps.executeUpdate();
            return result > 0;  // Nếu câu lệnh INSERT thành công, trả về true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Nếu có lỗi, trả về false
        }
    }
    public List<TheMuonSach> getTableTheMuonSachfromSQL() {
        List<TheMuonSach> theMuonSachList = new ArrayList<>();
        String query = "SELECT tm.MaMuonSach, tm.MaSach, tm.MaHocSinh, tm.TrangThai, tm.NgayMuon, tm.NgayTra, " +
                "s.TenSach, hs.HoTen " +
                "FROM themuonsach tm " +
                "JOIN sach s ON tm.MaSach = s.MaSach " +
                "JOIN hocsinh hs ON tm.MaHocSinh = hs.MaHocSinh";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TheMuonSach theMuonSach = new TheMuonSach();
                theMuonSach.setMaMuonSach(rs.getInt("MaMuonSach"));

                // Lấy thông tin sách
                Sach sach = new Sach();
                sach.setMaSach(rs.getInt("MaSach"));
                sach.setTenSach(rs.getString("TenSach"));
                theMuonSach.setSach(sach);

                // Lấy thông tin học sinh
                HocSinh hocSinh = new HocSinh();
                hocSinh.setMaHocSinh(rs.getInt("MaHocSinh"));
                hocSinh.setHoTen(rs.getString("HoTen"));
                theMuonSach.setHocSinh(hocSinh);

                // Các thông tin còn lại
                theMuonSach.setTrangThai(rs.getInt("TrangThai"));
                theMuonSach.setNgayMuon(rs.getDate("NgayMuon"));
                theMuonSach.setNgayTra(rs.getDate("NgayTra"));

                theMuonSachList.add(theMuonSach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return theMuonSachList;
    }


}