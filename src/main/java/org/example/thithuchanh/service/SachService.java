package org.example.thithuchanh.service;

import org.example.thithuchanh.model.Sach;
import org.example.thithuchanh.model.TheMuonSach;

import java.util.List;

public interface SachService {


    List<Sach> getSachList();

    void decreaseBookStock_truMot(String masach);

    boolean addtoTableTheMuonSachString( String mamuonsach, String mahocsinh, String masach, String ngaymuon, String ngaytra, String trangthai);

    public List<TheMuonSach> getTableTheMuonSach();
}