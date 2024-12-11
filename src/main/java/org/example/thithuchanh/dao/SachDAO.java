package org.example.thithuchanh.dao;

import org.example.thithuchanh.model.Sach;
import org.example.thithuchanh.model.TheMuonSach;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface SachDAO {
    public List<Sach> selectAllSach();
    void decreaseBookStock_truMotTrongSQL(String masach);

    boolean inserttoTableTheMuonSach(String mamuonsach, String mahocsinh, String masach, String ngaymuon, String ngaytra, String trangthai);
    public List<TheMuonSach> getTableTheMuonSachfromSQL();
}