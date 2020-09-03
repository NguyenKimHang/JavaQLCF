//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.UUID;
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.stage.Stage;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.JUnitCore;
//import org.junit.runner.Result;
//import org.junit.runner.RunWith;
//import org.junit.runner.notification.Failure;
//import org.junit.runners.Parameterized;
//import quanliquancaphe.DangNhapController;
//import quanliquancaphe.QuanLiBanController;
//import quanliquancaphe.QuanLiMonController;
//import quanliquancaphe.Ultils;
//import quanliquancaphe.models.Ban;
//import quanliquancaphe.models.NhanVien;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
///**
// *
// * @author admin
// */
////@RunWith(Parameterized.class)
//public class Tester {
////    @BeforeClass
////    public static void setUpClass() {
////        List<NhanVien> nv = new ArrayList<>();
////        nv = (ArrayList<NhanVien>) Ultils.getLogin();
////    }
//    
//    @Test
//    public void testLoginTenTKRong(){
//        DangNhapController dn = new DangNhapController();
//        boolean a = dn.DangNhap("", "123456",Ultils.getLogin());
//        boolean e = false;
//        Assert.assertEquals(e, a);
//             
//    }
//    @Test
//    public void testLoginTenMKRong(){
//        DangNhapController dn = new DangNhapController();
//        boolean a = dn.DangNhap("kthanh09", "",Ultils.getLogin());
//        boolean e = false;
//        Assert.assertEquals(e, a);
//             
//    }
//    
////    @Test
////    public void testLoginTenTKVaMKRong(){
////        DangNhapController dn = new DangNhapController();
////        boolean a = dn.DangNhap("", "",Ultils.getLogin());
////        boolean e = false;
////        Assert.assertEquals(e, a);
////             
////    }
//    
//    @Test
//    public void testLoginTKHon25KyTu(){
//        DangNhapController dn = new DangNhapController();
//        boolean a = dn.DangNhap("kthanhhhhhhhhhhhhhhhhhhhhh", "123456",Ultils.getLogin());
//        boolean e = false;
//        Assert.assertEquals(e, a);
//             
//    }
//    @Test
//    public void testLoginTenMK25KyTu(){
//        DangNhapController dn = new DangNhapController();
//        boolean a = dn.DangNhap("kthanh09", "1234567890123456789012345",Ultils.getLogin());
//        boolean e = false;
//        Assert.assertEquals(e, a);
//             
//    }
//    
//    @Test
//    public void testLoginTKKhongTonTai(){
//        DangNhapController dn = new DangNhapController();
//        boolean a = dn.DangNhap("kthanh009", "123456",Ultils.getLogin());
//        boolean e = false;
//        Assert.assertEquals(e, a);
//             
//    }
//    
//    
//    @Test(expected = ExceptionInInitializerError.class)
//    public void testLoginDung(){
//        DangNhapController dn = new DangNhapController();
//        boolean a = dn.DangNhap("kthanh09", "123456",Ultils.getLogin());
//        boolean e = true;
//        Assert.assertEquals(e, a);
//             
//    }
//    // Bàn
//    @Test
//    public void testThemBanSucChuaHon11KyTu(){
//        QuanLiBanController ban = new QuanLiBanController();
//        boolean a = ban.addBan("", 12, "");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testThemBanThatBai(){
//        QuanLiBanController ban = new QuanLiBanController();
//        boolean a = ban.addBan("", 0, "");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test @Ignore
//    public void testThemBanThanhCong(){
//        QuanLiBanController ban = new QuanLiBanController();
//        String m = UUID.randomUUID().toString();
//        boolean a = ban.addBan(m, 5, "Hết chỗ");
//        boolean e = true;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test 
//    public void testCapNhatBanThongTinTrong(){
//        QuanLiBanController ban = new QuanLiBanController();
//        boolean a = ban.upDateBan("", 0, "");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    
//    public void testCapNhatBanThatBai(){
//        QuanLiBanController ban = new QuanLiBanController();
//        boolean a = ban.upDateBan("", 10 , "Hết chỗ");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//        
//    }
//    
//    @Test
//    public void testCapNhatBanThanhCong(){
//        QuanLiBanController ban = new QuanLiBanController();
//        boolean a = ban.upDateBan("h3g4k4ijdygdajhaywq", 10 , "Hết chỗ");
//        boolean e = true;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testXoaRong(){
//        QuanLiBanController ban = new QuanLiBanController();
//        boolean a = ban.deleteBan("", 2, "");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test @Ignore
//    public void testXoaThanhCong(){
//        QuanLiBanController ban = new QuanLiBanController();
//        boolean a = ban.deleteBan("09b17881-6343-435d-bb39-ce146e670abb", 11, "Hết chỗ");
//        boolean e = true;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test 
//    public void testXoaThatBai(){
//        QuanLiBanController ban = new QuanLiBanController();
//        boolean a = ban.deleteBan("", 4, "Hết chỗ");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//
//    }
//    
//    // Món
//    @Test
//    public void testThemTenMonRong(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.addMon("3432kjkj", "", 0.0, "Còn hàng", "noel", "thức ăn");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testThemMonGiaBanKhongHopLe(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.addMon("3432kjkj", "trà đào", 0, "Còn hàng", "noel", "thức ăn");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testThemMonGiaBanNhoHon0(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.addMon("3432kjkj", "trà đào", -1, "Còn hàng", "noel", "thức ăn");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testThemMonTinhTrangTrong(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.addMon("3432kjkj", "trà đào", 50000, "", "noel", "đồ uống");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testThemMonThoiDiemRong(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.addMon("3432kjkj", "trà đào", 50000, "còn hàng", "", "đồ uống");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testThemMonLoaiRong(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.addMon("3432kjkj", "trà đào", 50000, "còn hàng", "noel", "");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testThemMonThatBai(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.addMon("", "", 0, "", "", "");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//     @Test @Ignore
//    public void testThemMonThanhCong(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.addMon("3432kjkj-0383gjjkj-37632-jkadn", "trà đào", 50000, "còn hàng", "noel", "đồ uống");
//        boolean e = true;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testCapNhatMonThatBai(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.upDateMon("", "", 0, "", "", "");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testCapNhatTenMonRong(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.upDateMon("5367352-hgvnbnm-3232e-8888","", 500000, "còn hàng", "noel", "đồ uống");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testCapNhatMaMonRong(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.upDateMon("", "trà đào", 500000, "còn hàng", "noel", "đồ uống");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testCapNhatTinhTrangMonRong(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.upDateMon("37489374932-hbdshmfsdb", "trà đào", 500000, "", "noel", "đồ uống");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testCapNhatThoiDiemMonRong(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.upDateMon("37489374932-hbdshmfsdb", "trà đào", 500000, "còn hàng", "", "đồ uống");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testCapNhatLoaiMonRong(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.upDateMon("37489374932-hbdshmfsdb", "trà đào", 500000, "còn hàng", "noel", "");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    @Test
//    public void testCapNhatGiaBanRong(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.upDateMon("37489374932-hbdshmfsdb", "trà đào", 0, "còn hàng", "noel", "");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testCapNhatMonThanhCong(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.upDateMon("ee2ba9d2-658f-4520-b2d3-0633d9d7d86c", "trà hạt chia", 50000.0, "còn hàng", "noel", "đồ uống");
//        boolean e = true;
//        Assert.assertEquals(e, a);
//    }
//    
//    @Test
//    public void testXoaMonRong(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.deleteMon("", "", 0, "", "", "");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    @Test @Ignore
//    public void testXoaMonThanhCong(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.deleteMon("ee2ba9d2-658f-4520-b2d3-0633d9d7d86c", "trà hạt chia",
//                50000, "còn hàng", "noel", "đồ uống");
//        boolean e = true;
//        Assert.assertEquals(e, a);
//    }
//
//    @Test
//    public void testXoaMonThatBai(){
//        QuanLiMonController mon = new QuanLiMonController();
//        boolean a = mon.deleteMon("", "trà hạt chia", 50000, "còn hàng", "noel", "đồ uống");
//        boolean e = false;
//        Assert.assertEquals(e, a);
//    }
//    
//    
//    
//}
