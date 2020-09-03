/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "nhanvien")
public class NhanVien implements Serializable {

    @Id
    @Column(name = "maNV")
    private String ma;
    @Column(name = "ten")
    private String ten;
    @Column(name = "gioiTinh")
    private String gioiTinh;
    @Column(name = "queQuan")
    private String queQuan;
    @Column(name = "ngaySinh")
    private Date ngaySinh;
    @Column(name = "tenTaiKhoan")
    private String tenTaiKhoan;
    @Column(name = "matKhau")
    private String matKhau;

    public NhanVien() {

    }

    public NhanVien(String ttk, String mk) {
        this.tenTaiKhoan = ttk;
        this.matKhau = mk;
    }

    public NhanVien(String ma, String ten, String gt, String qq, String ns, String ttk, String mk) {
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy/mm/dd");
            this.ma = ma;
            this.ten = ten;
            this.gioiTinh = gt;
            this.queQuan = qq;
            this.ngaySinh = f.parse(ns);
            this.tenTaiKhoan = ttk;
            this.matKhau = mk;

        } catch (ParseException ex) {
            Logger.getLogger(NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @return the ma
     */
    public String getMa() {
        return ma;
    }

    /**
     * @param aMa the ma to set
     */
    public void setMa(String aMa) {
        ma = aMa;
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
     * @return the gioiTinh
     */
    public String getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the queQuan
     */
    public String getQueQuan() {
        return queQuan;
    }

    /**
     * @param queQuan the queQuan to set
     */
    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    /**
     * @return the ngaySinh
     */
    public Date getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    /**
     * @return the tenTaiKhoan
     */
    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    /**
     * @param tenTaiKhoan the tenTaiKhoan to set
     */
    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    /**
     * @return the matKhau
     */
    public String getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

}
