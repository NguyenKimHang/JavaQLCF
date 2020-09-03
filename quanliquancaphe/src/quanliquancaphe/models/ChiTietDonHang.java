/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "chitietdonhang")
public class ChiTietDonHang implements Serializable {

    

    @Column(name = "tenMon")
    private String tenMon;
    @Column(name = "soLuong")
    private int soLuong;
    @Column(name = "donGia")
    private double donGia;
    @Column (name = "thanhTien")
    private double thanhTien;
    
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "maMon")
    private Mon idMon;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hoadon_maHoaDon")
    private HoaDon idHD;

    public ChiTietDonHang() {

    }

    public ChiTietDonHang(String tenM, int sl, double dg, Mon maM, HoaDon maHD,
            double thanhTien) {
        this.tenMon = tenM;
        this.soLuong = sl;
        this.donGia = dg;
        this.idHD = maHD;
        this.idMon = maM;
        this.thanhTien = thanhTien;
    }
    
    public double thanhTien(){
        return this.donGia*this.soLuong;
    }

    /**
     * @return the tenMon
     */
    public String getTenMon() {
        return tenMon;
    }

    /**
     * @param tenMon the tenMon to set
     */
    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the donGia
     */
    public double getDonGia() {
        return donGia;
    }

    /**
     * @param donGia the donGia to set
     */
    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    /**
     * @return the idMon
     */
    public Mon getIdMon() {
        return idMon;
    }

    /**
     * @param idMon the idMon to set
     */
    public void setIdMon(Mon idMon) {
        this.idMon = idMon;
    }

    /**
     * @return the idHD
     */
    public HoaDon getIdHD() {
        return idHD;
    }

    /**
     * @param idHD the idHD to set
     */
    public void setIdHD(HoaDon idHD) {
        this.idHD = idHD;
    }
    
    /**
     * @return the thanhTien
     */
    public double getThanhTien() {
        return thanhTien;
    }

    /**
     * @param thanhTien the thanhTien to set
     */
    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
}
