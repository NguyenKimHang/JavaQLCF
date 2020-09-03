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
@Table(name = "ban")
public class Ban implements Serializable {

    @Id
    @Column(name = "maBan")
    private String maBan;
    @Column(name = "tenBan")
    private String tenBan;
    @Column(name = "sucChua")
    private int sucChua;
    @Column(name = "tinhTrangTrong")
    private String tinhTrangTrong;
    @Column(name = "viTri")
    private String viTriBan;

    @OneToMany(mappedBy = "maBan")
    private Set<HoaDon> hd;

    public Ban(String m, String t, int sc, String tinhTrangTrong, String viTri) {
        this.maBan = m;
        this.tenBan = t;
        this.sucChua = sc;
        this.tinhTrangTrong = tinhTrangTrong;
        this.viTriBan = viTri;
    }

    public Ban() {

    }

    /**
     * @return the maBan
     */
    public String getMaBan() {
        return maBan;
    }

    /**
     * @param maBan the maBan to set
     */
    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    /**
     * @return the sucChua
     */
    public int getSucChua() {
        return sucChua;
    }

    /**
     * @param sucChua the sucChua to set
     */
    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    /**
     * @return the tinhTrangTrong
     */
    public String getTinhTrangTrong() {
        return tinhTrangTrong;
    }

    /**
     * @param tinhTrangTrong the tinhTrangTrong to set
     */
    public void setTinhTrangTrong(String tinhTrangTrong) {
        this.tinhTrangTrong = tinhTrangTrong;
    }

    /**
     * @return the viTriBan
     */
    public String getViTriBan() {
        return viTriBan;
    }

    /**
     * @param viTriBan the viTriBan to set
     */
    public void setViTriBan(String viTriBan) {
        this.viTriBan = viTriBan;
    }

    /**
     * @return the tenBan
     */
    public String getTenBan() {
        return tenBan;
    }

    /**
     * @param tenBan the tenBan to set
     */
    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    /**
     * @return the hd
     */
    public Set<HoaDon> getHd() {
        return hd;
    }

    /**
     * @param hd the hd to set
     */
    public void setHd(Set<HoaDon> hd) {
        this.hd = hd;
    }

}
