package org.example.thithuchanh.service;

import org.example.thithuchanh.dao.SachDAO;
import org.example.thithuchanh.dao.SachDAOImpl;
import org.example.thithuchanh.model.Sach;
import org.example.thithuchanh.model.TheMuonSach;

import java.util.List;

public class SachServiceImpl implements SachService {
    private SachDAO sachDAO;

    public SachServiceImpl() {
        this.sachDAO = new SachDAOImpl();
    }

    public List<Sach> getSachList() {
        return sachDAO.selectAllSach();
    }

    public void decreaseBookStock_truMot(String masach) {
        sachDAO.decreaseBookStock_truMotTrongSQL(masach);
    }

   public boolean addtoTableTheMuonSachString(String mamuonsach, String mahocsinh, String masach, String ngaymuon, String ngaytra, String trangthai) {
        sachDAO.inserttoTableTheMuonSach(mamuonsach, mahocsinh, masach, ngaymuon,ngaytra, trangthai);
       return false;
   }

    public List<TheMuonSach> getTableTheMuonSach() {
        List<TheMuonSach> tableTheMuonSachfromSQL = sachDAO.getTableTheMuonSachfromSQL();
        return tableTheMuonSachfromSQL;
    }




}


