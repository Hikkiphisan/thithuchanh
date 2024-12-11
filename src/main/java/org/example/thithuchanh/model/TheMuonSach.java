package org.example.thithuchanh.model;

import java.util.Date;

public class TheMuonSach {
    private int maMuonSach;        // Mã mượn sách
    private Sach sach;             // Thông tin sách mượn
    private HocSinh hocSinh;       // Thông tin học sinh mượn sách
    private int trangThai;         // Trạng thái (0: đã trả, 1: đang mượn)
    private Date ngayMuon;         // Ngày mượn sách
    private Date ngayTra;          // Ngày trả sách (có thể là null)

    // Constructor mặc định
    public TheMuonSach() {}

    // Constructor với tất cả các tham số
    public TheMuonSach(int maMuonSach, Sach sach, HocSinh hocSinh, int trangThai, Date ngayMuon, Date ngayTra) {
        this.maMuonSach = maMuonSach;
        this.sach = sach;
        this.hocSinh = hocSinh;
        this.trangThai = trangThai;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
    }

    // Getters và Setters
    public int getMaMuonSach() {
        return maMuonSach;
    }

    public void setMaMuonSach(int maMuonSach) {
        this.maMuonSach = maMuonSach;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public HocSinh getHocSinh() {
        return hocSinh;
    }

    public void setHocSinh(HocSinh hocSinh) {
        this.hocSinh = hocSinh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    @Override
    public String toString() {
        return "TheMuonSach{" +
                "maMuonSach=" + maMuonSach +
                ", sach=" + sach.getTenSach() + // Chỉ lấy tên sách
                ", hocSinh=" + hocSinh.getHoTen() + // Chỉ lấy tên học sinh
                ", trangThai=" + trangThai +
                ", ngayMuon=" + ngayMuon +
                ", ngayTra=" + ngayTra +
                '}';
    }
}
