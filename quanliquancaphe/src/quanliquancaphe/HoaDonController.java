/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import static quanliquancaphe.Ultils.factory;
import quanliquancaphe.models.Ban;
import quanliquancaphe.models.HoaDon;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class HoaDonController implements Initializable {

    @FXML
    private Button btThoat;
    @FXML
    private DatePicker dateNgayDat;
    @FXML
    private DatePicker dateNgayThanhToan;
    @FXML
    private TableView<HoaDon> tbHoaDon;
    @FXML
    private TableColumn colMaHoaDon;
    @FXML
    private TableColumn colNgayDat;
    @FXML
    private TableColumn colNgayThanhToan;
    @FXML
    private TableColumn colTongTien;
    @FXML
    private Button btTim;
    private String date;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.colMaHoaDon.setCellValueFactory(new PropertyValueFactory("maHD"));
        this.colNgayDat.setCellValueFactory(new PropertyValueFactory("ngayDat"));
        this.colNgayThanhToan.setCellValueFactory(new PropertyValueFactory("ngayThanhToan"));
        this.colTongTien.setCellValueFactory(new PropertyValueFactory("tongTien"));

        this.reload("");
        

    }

    private void reload(String kw) {
        this.tbHoaDon.getItems().clear();
        this.tbHoaDon.setItems((ObservableList<HoaDon>) Ultils.getListHD(kw));
    }

//    @FXML
//    private void timKiemTheoNgay(ActionEvent event){
//         Date ngStart = Date.from(dateNgayDat.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//         
//         
//             this.reload("");
//     
//    }
    @FXML
    private void UpdateHoaDon(ActionEvent event) {
        Ultils.hd = this.tbHoaDon.getSelectionModel().getSelectedItem();
        Date ngStart = Date.from(dateNgayDat.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date ngTT = Date.from(dateNgayThanhToan.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        if (Ultils.hd.getMaBan() != null) {
            HoaDon h = new HoaDon();
            h.setMaHD(Ultils.hd.getMaHD());
            h.setMaBan(Ultils.hd.getMaBan());
            h.setNgayDat(ngStart);
            h.setNgayThanhToan(ngTT);
            h.setTongTien(Ultils.hd.getTongTien());

            Ultils.addOrUpdateHoaDon(h);
        }
    }

    @FXML
    private void XuatHoaDonHandler(ActionEvent event) throws IOException {
        Ultils.hd = tbHoaDon.getSelectionModel().getSelectedItem();
        if (Ultils.hd == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Cảnh báo ");
            a.setContentText("Chưa có hóa đơn để in ");
            a.show();
        } else {
            Parent root = FXMLLoader.load((getClass().getResource("ChiTietDonHang.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    private void troVeTrangChinh(ActionEvent event) {
        try {
            Parent login = FXMLLoader.load(getClass().getResource("GiaoDienChinh.fxml"));
            Scene loginScene = new Scene(login);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void btThoatHandler(ActionEvent event) throws IOException {
        btThoat.setOnMouseClicked(et -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Bạn chắc chưa?");
            alert.showAndWait().ifPresent(bt -> {
                if (bt == ButtonType.OK) {
                    try {
                        Parent login = FXMLLoader.load(getClass().getResource("DangNhap.fxml"));
                        Scene loginScene = new Scene(login);

                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(loginScene);
                        window.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            });
        });
    }

}
