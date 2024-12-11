package org.example.thithuchanh.service;

import org.example.thithuchanh.dao.SachDAO;
import org.example.thithuchanh.dao.SachDAOImpl;
import org.example.thithuchanh.model.Sach;

import java.util.List;

public class SachServiceImpl implements SachService {
    private SachDAO sachDAO;

    public SachServiceImpl() {
        this.sachDAO = new SachDAOImpl();
    }

    public List<Sach> getSachList() {
        return sachDAO.selectAllSach();
    }
}
