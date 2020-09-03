/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe.models;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "mon")
public class Mon implements Serializable {

    @Id
    @Column(name = "maMon")
    private String maMon;

    @Column(name = "ten")
    private String ten;

    @Column(name = "giaBan")
    private double giaBan;

    @Column(name = "tinhTrangCon")
    private String tinhTrangCon;

    @Column(name = "thoiDiemBan")
    private String thoiDiemBan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "danhmuc_id")
    private DanhMuc loai;

//    @OneToMany(mappedBy = "idMon", cascade = CascadeType.ALL)
//    private Set<ChiTietDonHang> ctdh;

    public Mon() {

    }

    public Mon(String m, String ten, double gia, String con, String thoiDiemBan,
            DanhMuc l) {
        this.maMon = m;
        this.ten = ten;
        this.giaBan = gia;
        this.tinhTrangCon = con;
        this.thoiDiemBan = thoiDiemBan;
        this.loai = l;
    }


    
    
    

    /**
     * @return the ten
     */
    public String getTen() {
        return ten;
    }

    /**
     * @param ten the ten to set
     */
    public void setTen(String ten) {
        this.ten = ten;
    }

    /**
     * @return the giaBan
     */
    public double getGiaBan() {
        return giaBan;
    }

    /**
     * @param giaBan the giaBan to set
     */
    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    /**
     * @return the tinhTrangCon
     */
    public String isTinhTrangCon() {
        return tinhTrangCon;
    }

    /**
     * @param tinhTrangCon the tinhTrangCon to set
     */
    public void setTinhTrangCon(String tinhTrangCon) {
        this.tinhTrangCon = tinhTrangCon;
    }

    /**
     * @return the thoiDiemBan
     */
    public String getThoiDiemBan() {
        return thoiDiemBan;
    }

    /**
     * @param thoiDiemBan the thoiDiemBan to set
     */
    public void setThoiDiemBan(String thoiDiemBan) {
        this.thoiDiemBan = thoiDiemBan;
    }

    /**
     * @return the maMon
     */
    public String getMaMon() {
        return maMon;
    }

    /**
     * @param maMon the maMon to set
     */
    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    /**
     * @return the loai
     */
    public DanhMuc getLoai() {
        return loai;
    }

    /**
     * @param loai the loai to set
     */
    public void setLoai(DanhMuc loai) {
        this.loai = loai;
    }

}
