package org.example.thithuchanh.dao;

import org.example.thithuchanh.model.Sach;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface SachDAO {
    public List<Sach> selectAllSach();
}