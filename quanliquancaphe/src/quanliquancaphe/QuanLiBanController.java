/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe;

import com.sun.javafx.util.Utils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import quanliquancaphe.hibernate.HibernateUtils;
import quanliquancaphe.models.Ban;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuanLiBanController implements Initializable {

    @FXML
    private TextField txtMaBan;
    @FXML
    private TextField txtTenBan;
    @FXML
    private TextField txtSucChua;
    @FXML
    private ComboBox<String> cbTinhTrang;
    @FXML
    private TextField txtViTriBan;
    @FXML
    private TextField txtNhapTimKiem;
    @FXML
    private TableView<Ban> tbBan;
    @FXML
    private TableColumn colTenBan;
    @FXML
    private TableColumn colViTri;
    @FXML
    private TableColumn colSucChua;
    @FXML
    private TableColumn colTinhTrang;
    @FXML
    private Button btTrangChu;
    @FXML
    private Button btQLMon;
    @FXML
    private Button btUpdate;
    @FXML
    private Button btDel;

    @FXML
    private Button btThoat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.cbTinhTrang.getItems().add("Còn trống");
        this.cbTinhTrang.getItems().add("Đã đặt");
        this.cbTinhTrang.getSelectionModel().selectFirst();

        //Gọi observable, thêm vào các trường
        this.colTenBan.setCellValueFactory(new PropertyValueFactory("tenBan"));
        this.colSucChua.setCellValueFactory(new PropertyValueFactory("sucChua"));
        this.colTinhTrang.setCellValueFactory(new PropertyValueFactory("tinhTrangTrong"));
        this.colViTri.setCellValueFactory(new PropertyValueFactory("viTriBan"));

        this.tbBan.setItems(this.getListBan(""));

        //Tìm kiếm
        this.txtNhapTimKiem.textProperty().addListener(et -> {
            this.reLoad(this.txtNhapTimKiem.getText());
        });

        //Sự kiện click trên dòng
        this.tbBan.setRowFactory(tbb -> {
            TableRow<Ban> row = new TableRow<>();
            row.setOnMouseClicked(r -> {
                // Đúp click
                if (r.getClickCount() == 2) {
                    Ban b = row.getItem();
                    this.txtMaBan.setText(b.getMaBan());
                    this.txtTenBan.setText(b.getTenBan());
                    this.txtSucChua.setText(String.valueOf(b.getSucChua()));
                    this.cbTinhTrang.setValue(b.getTinhTrangTrong());
                    this.txtViTriBan.setText(b.getViTriBan());
                }
            });
            return row;

        });

    }

    private void clear() {
        this.txtMaBan.clear();
        this.txtSucChua.clear();
        this.txtViTriBan.clear();
        this.txtTenBan.clear();

    }

    private void reLoad(String kw) {
        this.tbBan.getItems().clear();
        this.tbBan.setItems(this.getListBan(kw));
    }

    @FXML
    private void addBanAction(ActionEvent event) {

        String m = UUID.randomUUID().toString();

        if (txtTenBan.getText().isEmpty() || txtSucChua.getText().isEmpty()
                || txtViTriBan.getText().isEmpty()
                || cbTinhTrang.getSelectionModel().getSelectedItem().isEmpty()) {
            Ultils.getAlert("Thông tin bàn trống !!!", Alert.AlertType.WARNING);
        } else {
            if (!Ultils.checknumber(this.txtSucChua.getText())) {
                Ultils.getAlert("Sức chứa phải là số!!", Alert.AlertType.WARNING);
            } else if (Integer.parseInt(this.txtSucChua.getText()) <= 0
                    || Integer.parseInt(this.txtSucChua.getText()) > 11) {
                Ultils.getAlert("Sức chứa tối đa 11 chỗ!!!", Alert.AlertType.WARNING);
            } else {

                if (this.addBan(m, this.txtTenBan.getText(),
                        Integer.parseInt(this.txtSucChua.getText()),
                        this.cbTinhTrang.getSelectionModel().getSelectedItem().toString(),
                        this.txtViTriBan.getText())) {
                    Ultils.getAlert("Thành công !!!", Alert.AlertType.INFORMATION);
                    this.clear();
                    this.reLoad("");

                } else {

                    Alert a = new Alert(Alert.AlertType.WARNING, "Thêm thất bại", ButtonType.OK);
                    a.show();
                }

            }

        }

    }

    @FXML
    private void updateBanAction(ActionEvent event) {

        this.tbBan.getSelectionModel().getSelectedItem();

        if (txtMaBan.getText().isEmpty() || txtTenBan.getText().isEmpty()
                || txtSucChua.getText().isEmpty()) {
            Ultils.getAlert("Thông tin trống !!!", Alert.AlertType.WARNING);
        } else {
            if (!Ultils.checknumber(this.txtSucChua.getText())) {
                Ultils.getAlert("Sức chứa phải là số!!", Alert.AlertType.WARNING);
            } else if (Integer.parseInt(this.txtSucChua.getText()) <= 0
                    || Integer.parseInt(this.txtSucChua.getText()) > 11) {
                Ultils.getAlert("Sức chứa tối đa 11 chỗ!!!", Alert.AlertType.WARNING);
            } else if (this.upDateBan(txtMaBan.getText(), txtTenBan.getText(),
                    Integer.parseInt(txtSucChua.getText()),
                    cbTinhTrang.getSelectionModel().getSelectedItem().toString(),
                    this.txtViTriBan.getText())) {
                Ultils.getAlert("Thành công !!!", Alert.AlertType.INFORMATION);
                this.clear();
                this.reLoad("");
            } else {
                Ultils.getAlert("Thât Bại !!!", Alert.AlertType.ERROR);
            }

        }

    }

    @FXML
    private void deleteBanAction(ActionEvent event) {
        Ban b = this.tbBan.getSelectionModel().getSelectedItem();
        if (txtMaBan.getText().isEmpty()) {
            Ultils.getAlert("Thông tin trống !!!", Alert.AlertType.WARNING);
        } else {
            try {
                btDel.setOnMouseClicked(et -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Bạn chắc chưa?");
                    alert.showAndWait().ifPresent(bt -> {
                        if (bt == ButtonType.OK) {
                            if (this.deleteBan(txtMaBan.getText(), txtTenBan.getText(),
                                    Integer.parseInt(txtSucChua.getText()),
                                    cbTinhTrang.getSelectionModel().getSelectedItem().toString(),
                                    this.txtViTriBan.getText())) {
                                //                Alert a = new Alert(Alert.AlertType.INFORMATION, "Thành công", ButtonType.OK);
//                a.show();
                                this.clear();
                                this.reLoad("");
                            }
                        }

                    });

                });
            } catch (Exception ex) {
                Logger.getLogger(QuanLiBanController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    // Giai quyết click vào khi chưa có dữ liệu ?
    public boolean deleteBan(String m, String t, int sc, String tt, String vt) {
        boolean rs = false;
        Ban b = new Ban(m, t, sc, tt, vt);

        if (m.isEmpty()) {

            rs = false;
        } else {

            Ultils.deleteBans(b);

            rs = true;

        }
        return rs;
    }

    public boolean addBan(String ma, String t, int sc, String tt, String vt) {
        boolean rs = false;
        Ban ban = new Ban(ma, t, sc, tt, vt);
        try {
            if (!t.isEmpty() && (sc > 0 || sc < 11) && !tt.isEmpty() && !vt.isEmpty()) {

                Ultils.addOrUpdateBan(ban);
                rs = true;
            }
        } catch (ExceptionInInitializerError ex) {
            System.err.println(ex.getMessage());
        }
        return rs;

    }

// Note: chưa kiểm tra đầu vào
    public boolean upDateBan(String m, String t, int sc, String tt, String vt) {
        boolean rs = false;

        //Lấy bàn, trả về Ban
        if (!m.isEmpty()) {

            try {
                Ban b = new Ban();
                b.setMaBan(m);
                b.setTenBan(t);
                b.setSucChua(sc);
                b.setTinhTrangTrong(tt);
                b.setViTriBan(vt);

                Ultils.addOrUpdateBan(b);
                rs = true;
            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
                rs = false;
            }

        }
        return rs;
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

    @FXML
    private void denQLMon(ActionEvent event) {
        try {
            Parent login = FXMLLoader.load(getClass().getResource("QuanLiMon.fxml"));
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

    //FXCollections dùng ép kiểu observable
    private ObservableList getListBan(String kw) {
        Session sessions = HibernateUtils.getSessionFactory().openSession();
        Criteria cr = sessions.createCriteria(Ban.class);
        cr.addOrder(Order.asc("tenBan"));
        if (kw != null && !kw.equals("")) {
            kw = String.format("%%%s%%", kw);

            cr.add(Restrictions.ilike("tenBan", kw));
        }
        List<Ban> bans = cr.list();
        sessions.close();

        return FXCollections.observableArrayList(bans);

    }

}
