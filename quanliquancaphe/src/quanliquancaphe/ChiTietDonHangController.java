/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import quanliquancaphe.models.ChiTietDonHang;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ChiTietDonHangController implements Initializable {

    @FXML
    private Label lbTenBan;
    @FXML
    private Label lbNgayDat;
    @FXML
    private Label lbNgayThanhToan;
    @FXML
    private Label lbMaHD;
    @FXML
    private Label lbTongTien;
    @FXML
    private GridPane gpHD;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
//        lbTenBan.setText(Ultils.b.getTenBan()) = Ultils.getTenBan(Ultils.b);
        lbMaHD.setText(Ultils.hd.getMaHD());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        lbNgayDat.setText(df.format(Ultils.hd.getNgayDat()));
        lbNgayThanhToan.setText(df.format(Ultils.hd.getNgayThanhToan()));
        List<ChiTietDonHang> lscthd = Ultils.laydsCTHD(Ultils.hd);
        int size = lscthd.size();
        if (size == 0) {
            gpHD.add(setLB(String.format("%s", Ultils.cthd.getTenMon())), 0, 1);
            gpHD.add(setLB(String.format("%s",Ultils.cthd.getSoLuong())), 1, 1);
            gpHD.add(setLB(String.format("%s",Ultils.cthd.getDonGia())), 2, 1);
            gpHD.add(setLB(String.format("%,.2f", Ultils.cthd.getThanhTien())), 3, 1);
//            double tien = Ultils.cthd.getDonGia() * Ultils.cthd.getSoLuong();
//            gpHD.add(setLB(String.format("%,.2f", tien)), 4, 1);

        } else {
            for (int i = 0; i < size; i++) {
                gpHD.add(setLB(String.format("%s", lscthd.get(i).getTenMon())), 0, i + 1);
                gpHD.add(setLB(String.format("%s",lscthd.get(i).getSoLuong())), 1, i + 1);
                gpHD.add(setLB(String.format("%s",lscthd.get(i).getDonGia())), 2, i + 1);
                gpHD.add(setLB(String.format("%,.2f", lscthd.get(i).getThanhTien())), 3, i + 1);
//                double tien = lscthd.get(i).getDonGia() * lscthd.get(i).getSoLuong();
//                gpHD.add(setLB(String.format("%,.2f", tien)), 4, i + 1);
            }
        }
        lbTongTien.setText(String.format("%,.2f%s", Ultils.hd.getTongTien(), " VNĐ"));
    }

    public Label setLB(String s) {
        return new Label(s);
    }

}
