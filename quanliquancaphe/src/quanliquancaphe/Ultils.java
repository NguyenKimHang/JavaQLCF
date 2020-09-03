/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import quanliquancaphe.hibernate.HibernateUtils;
import quanliquancaphe.models.Ban;
import quanliquancaphe.models.ChiTietDonHang;
import quanliquancaphe.models.HoaDon;
import quanliquancaphe.models.DanhMuc;
import quanliquancaphe.models.Mon;
import quanliquancaphe.models.NhanVien;

/**
 *
 * @author admin
 */
public class Ultils {

    public static final SessionFactory factory = HibernateUtils.getSessionFactory();
    public static HoaDon hd = null;
    public static String id = "";
    public static ChiTietDonHang cthd = null;
    public static Ban b;
    public static Mon m;

    public static void addOrUpdateBan(Ban b) {
        Session session = factory.openSession();

        Transaction trans = session.beginTransaction();

        session.saveOrUpdate(b);

        trans.commit();

        session.close();
    }

    public static boolean deleteBans(Ban b) {
        Session session = factory.openSession();

        Transaction trans = session.beginTransaction();

         try {
            session.delete(b);
            trans.commit();
            return true;
        } catch (Exception e) {
            Ultils.getAlert("Xóa Thất bại. Không thể xóa", Alert.AlertType.ERROR);
            session.close();
            return false;
        }
    }

    public static boolean deleteCTDH(ChiTietDonHang b) {
        Session session = factory.openSession();

        Transaction trans = session.beginTransaction();

        try {
            session.delete(b);
            trans.commit();
            return true;
        } catch (Exception e) {
            Ultils.getAlert("Xóa Thất bại. Không thể xóa", Alert.AlertType.ERROR);
            session.close();
            return false;
        }
    }

    public static void addOrUpdateMon(Mon m) {
        Session session = factory.openSession();

        Transaction trans = session.beginTransaction();

        session.saveOrUpdate(m);

        trans.commit();

        session.close();
    }

    public static boolean deleteMons(Mon m) {

        Session session = factory.openSession();

        Transaction trans = session.beginTransaction();
        try {
            session.delete(m);
            trans.commit();
            return true;
        } catch (Exception e) {
            Ultils.getAlert("Xóa Thất bại", Alert.AlertType.ERROR);
            session.close();
            return false;
        }

    }

    public static Alert getAlert(String content, Alert.AlertType type) {
        Alert a = new Alert(type);
        a.setContentText(content);
        a.show();
        return a;
    }

//    public static int countBan() {
//        Session session = factory.openSession();
//
//        Criteria cr = session.createCriteria(Ban.class);
//        cr.setProjection(Projections.count("maBan"));
//
//        List rs = cr.list();
//
//        session.close();
//
//        return Integer.parseInt(rs.get(0).toString());
//    }
    public static List<DanhMuc> getDanhMuc() {
        Session session = factory.openSession();

        Criteria cr = session.createCriteria(DanhMuc.class);
        List<DanhMuc> danhmuc = cr.list();
        session.close();

        return danhmuc;
    }

    public static List<Ban> getBan() {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Ban.class);
        cr.addOrder(Order.asc("tenBan"));
        //cr.add(Restrictions.eq("tenBan", b));

        List<Ban> bans = cr.list();
        session.close();
        return bans;
    }

    public static List<Mon> getMon() {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Mon.class);
        //Mon mon = new Mon();
        //cr.add(Restrictions.eq("loai", m));

        List<Mon> mons = cr.list();
        session.close();
        return mons;
    }

    public static ObservableList getListMon(String kw) {
        Session sessions = factory.openSession();
        Criteria cr = sessions.createCriteria(Mon.class);
        cr.addOrder(Order.asc("ten"));
        if (kw != null && !kw.equals("")) {
            kw = String.format("%%%s%%", kw);

            cr.add(Restrictions.ilike("ten", kw));
        }
        List<Mon> mons = cr.list();
        sessions.close();

        return FXCollections.observableArrayList(mons);

    }

    public static ObservableList getListCTHD(String kw) {
        Session sessions = factory.openSession();
        Criteria cr = sessions.createCriteria(ChiTietDonHang.class);
        //cr.addOrder(Order.asc("tenMon"));
        if (kw != null && !kw.equals("")) {
            kw = String.format("%%%s%%", kw);

            cr.add(Restrictions.ilike("tenMon", kw));
        }
        List<ChiTietDonHang> mons = cr.list();
        sessions.close();

        return FXCollections.observableArrayList(mons);

    }

    public static List<HoaDon> getHoaDonByMaBan(String maBan) {

        Session session = factory.openSession();
        String hql = "Select maBan FROM HoaDon A, Ban B WHERE A.maBan = B.maBan and maBan = :maBan";
        Query q = session.createQuery(hql);
        q.setParameter("maBan", maBan);
        List<HoaDon> rs = q.list();
        session.close();

        return rs;
    }

    public static List<ChiTietDonHang> getCTDHByHD(HoaDon h) {
        Session session = factory.openSession();

        String hql = "Select * FROM ChiTietDonHang WHERE idHD = :h";
        Query q = session.createQuery(hql);
        q.setParameter("idHD", h);

        List<ChiTietDonHang> rs = q.list();

        session.close();
        return rs;
        //return FXCollections.observableArrayList(rs);
    }

    // lấy tên món, giá bán bằng danhmuc
    public static List<Mon> laydsMon(String dm) {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Mon.class);

        if (dm != null) {
            cr.add(Restrictions.ilike("maMon", dm));
        }
        List<Mon> ph = cr.list();
        session.close();
        return ph;
    }

    public static String getTenMon(String maMon) {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Mon.class);
        Mon mon = new Mon();
        cr.add(Restrictions.eq("tenMon", mon));

        List mons = cr.list();
        session.close();
        return String.valueOf(mons.get(0));
    }

    public static void addOrUpdateHoaDon(HoaDon h) {
        Session session = factory.openSession();

        Transaction trans = session.beginTransaction();

        session.saveOrUpdate(h);

        trans.commit();

        session.close();
    }

    public static void addOrUpdateCTDH(ChiTietDonHang h) {
        Session session = factory.openSession();

        Transaction trans = session.beginTransaction();

        session.saveOrUpdate(h);

        trans.commit();

        session.close();
    }

    public static String getMaBan(String tenBan) {
        Session session = factory.openSession();
        String hql = "Select maBan FROM Ban WHERE tenBan = :tenBan";
        Query q = session.createQuery(hql);
        q.setParameter("tenBan", tenBan);
        List rs = q.list();
        session.close();
        return String.valueOf(rs.get(0));

    }

//    public static String getMaHoaDon(String maBan) {
//        Session session = factory.openSession();
//        String hql = "Select maHD FROM HoaDon WHERE maBan = :maBan";
//        Query q = session.createQuery(hql);
//        q.setParameter("maBan", maBan);
//        List rs = q.list();
//        session.close();
//        return String.valueOf(rs.get(0));
//
//    }
//
//    public static String getTenBan(String mBan) {
//        Session session = factory.openSession();
//        String hql = "Select tenBan FROM Ban, HoaDon WHERE maBan = maBan";
//        Query q = session.createQuery(hql);
//        q.setParameter("maBan", mBan);
//        List rs = q.list();
//        session.close();
//        return String.valueOf(rs.get(0));
//
//    }
//
//    public static String getTTBan(String tenBan) {
//        Session session = factory.openSession();
//        String hql = "Select tinhTrangTrong FROM Ban WHERE tenBan = :tenBan";
//        Query q = session.createQuery(hql);
//        q.setParameter("tenBan", tenBan);
//        List rs = q.list();
//        session.close();
//        return String.valueOf(rs.get(0));
//
//    }
    public static List<HoaDon> getListHD(String kw) {
        Session sessions = factory.openSession();
        Criteria cr = sessions.createCriteria(HoaDon.class);
        cr.addOrder(Order.asc("ngayDat"));
        if (kw != null && !kw.equals("")) {
            kw = String.format("%%%s%%", kw);

            cr.add(Restrictions.ilike("ngayDat", kw));
        }

//        cr.add(Restrictions.ilike("maHD", String.format("%%%s%%", id)));
        List<HoaDon> hd = cr.list();
        sessions.close();
        return FXCollections.observableArrayList(hd);
    }

    public static List<HoaDon> laydsHD(String id) {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(HoaDon.class);

        if (id != null) {
            cr.add(Restrictions.ilike("maHD", id));

        }
        List<HoaDon> hd = cr.list();
        session.close();
        return hd;
    }

    public static List<ChiTietDonHang> laydsCTHD(HoaDon hd) {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(ChiTietDonHang.class);

        if (id != null) {
            cr.add(Restrictions.eq("idHD", hd));
        }
        List<ChiTietDonHang> lHD = cr.list();
        session.close();
        return lHD;
    }

    public static List<Ban> laydsBan() {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Ban.class);

//        if (id != null) {
//            cr.add(Restrictions.eq("idHD", hd));
//        }
        List<Ban> lHD = cr.list();
        session.close();
        return lHD;
    }

    public static boolean checknumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // hàm kiểm tra nhập năm thống kê

    public static boolean checkNam(String s) {
        boolean check = false;
        Pattern pattern = Pattern.compile("^\\d{1,5}$");
        Matcher mat = pattern.matcher(s);
        if (Integer.parseInt(s) > 0) {
            if (mat.find()) {
                check = true;
            }
        }

        return check;
    }

    public static ArrayList<Double> listDoanhThuT(int nam) throws ParseException {
        Session session = factory.openSession();
        ArrayList<Double> ls = new ArrayList<>(12);
        for (int i = 1; i <= 12; i++) {
            int thang = i;
            org.hibernate.Query q = session.createQuery("SELECT sum(A.tongTien)"
                    + " FROM HoaDon A"
                    + " WHERE (year(A.ngayThanhToan)=:nam and"
                    + " month(A.ngayThanhToan)=:thang)");
            q.setParameter("nam", nam);
            q.setParameter("thang", thang);

            List r = q.list();
            if (r.get(0) != null) {
                ls.add(i - 1, (Double) r.get(0));
            } else {
                ls.add(i - 1, 0.0);
            }
        }

        return ls;
    }

}
