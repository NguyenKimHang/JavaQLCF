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
import java.util.HashSet;
import java.util.Set;
import javafx.scene.control.DatePicker;
import javax.persistence.*;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "hoadon")
public class HoaDon implements Serializable {

    @Id
    @Column(name = "maHoaDon")
    private String maHD;
    @Column(name = "ngayDat")
    //@Temporal(javax.persistence.TemporalType.DATE)
    private Date ngayDat;
    @Column(name = "tongTien")
    private double tongTien;
    @Column(name = "ngayThanhToan")
    //@Temporal(javax.persistence.TemporalType.DATE)
    private Date ngayThanhToan;

    
    @ManyToOne
    @JoinColumn(name = "Ban_maBan")
    private Ban maBan;

//    @ManyToOne
//    @JoinColumn(name = "nhanvien_maNV")
//    private NhanVien maNV;

//    @OneToMany(mappedBy = "idHD", cascade = CascadeType.ALL)
//    private Set<ChiTietDonHang> ctdh;

    public HoaDon() {

    }

    public HoaDon(String ma, Ban maBan, Date ngayDat, Date ngayTT,
            double tt) {
//        try {
            this.maHD = ma;
            this.maBan = maBan;
//            this.maNV = maNV;
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");
            this.ngayDat = ngayDat;
            this.ngayThanhToan = ngayTT;
            this.tongTien = tt;
           
            //ctdh = new HashSet<>();
//        } catch (ParseException ex) {
//            System.err.println("Lỗi định dạng ngày tháng!!");
        
    }

    public HoaDon(String id, Ban b, DatePicker dateNgayDat, Date now, double tongTien) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the maHD
     */
    public String getMaHD() {
        return maHD;
    }

    /**
     * @param maHD the maHD to set
     */
    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    /**
     * @return the maBan
     */
    public Ban getMaBan() {
        return maBan;
    }

    /**
     * @param maBan the maBan to set
     */
    public void setMaBan(Ban maBan) {
        this.maBan = maBan;
    }

    /**
     * @return the ngayDat
     */
    public Date getNgayDat() {
        return ngayDat;
    }

    /**
     * @param ngayDat the ngayDat to set
     */
    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    /**
     * @return the tongTien
     */
    public double getTongTien() {
        return tongTien;
    }

    /**
     * @param tongTien the tongTien to set
     */
    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }


    /**
     * @return the ngayThanhToan
     */
    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    /**
     * @param ngayThanhToan the ngayThanhToan to set
     */
    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }


}
