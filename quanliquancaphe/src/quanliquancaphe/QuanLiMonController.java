/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLClientInfoException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.DataException;
import quanliquancaphe.hibernate.HibernateUtils;
import quanliquancaphe.models.Ban;
import quanliquancaphe.models.DanhMuc;
import quanliquancaphe.models.Mon;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuanLiMonController implements Initializable {

    @FXML
    private TextField txtmaMon;
    @FXML
    private TextField txtTenMon;
    @FXML
    private TextField txtGiaBan;
    @FXML
    private TextField txtThoiDiem;
    @FXML
    private TextField txtTimKiem;
    @FXML
    private ComboBox<String> cbTinhtrang;
    @FXML
    private ComboBox<DanhMuc> cbLoai;
    @FXML
    private TableView<Mon> tbMon;
    @FXML
    private TableColumn colMaMon;
    @FXML
    private TableColumn colTenMon;
    @FXML
    private TableColumn colTinhTrang;
    @FXML
    private TableColumn colGia;
    @FXML
    private TableColumn colThoiDiem;

    @FXML
    private TableColumn colLoai;

    private Button btTrangChu;
    @FXML
    private Button btQLBan;
    @FXML
    private Button btDel;
    @FXML
    private Button btThoat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbTinhtrang.getItems().add("Còn hàng");
        this.cbTinhtrang.getItems().add("Hết");
        this.cbTinhtrang.getSelectionModel().selectFirst();

//        this.cbLoai.getItems().add("Thức ăn");
//        this.cbLoai.getItems().add("Đồ uống");
        this.cbLoai.getItems().addAll(Ultils.getDanhMuc());
        this.cbLoai.getSelectionModel().selectFirst();

        //this.colMaMon.setCellValueFactory(new PropertyValueFactory("maMon"));
        this.colTenMon.setCellValueFactory(new PropertyValueFactory("ten"));
        this.colTinhTrang.setCellValueFactory(new PropertyValueFactory("tinhTrangCon"));
        this.colGia.setCellValueFactory(new PropertyValueFactory("giaBan"));
        this.colThoiDiem.setCellValueFactory(new PropertyValueFactory("thoiDiemBan"));
        this.colLoai.setCellValueFactory(new PropertyValueFactory("loai"));

        this.reLoad("");

//        Tìm kiếm
        this.txtTimKiem.textProperty().addListener(et -> {
            this.reLoad(this.txtTimKiem.getText());
        });

//        Sự kiện click trên dòng
        this.tbMon.setRowFactory(tbm -> {
            TableRow<Mon> row = new TableRow<>();
            row.setOnMouseClicked(r -> {
                // Đúp click
                if (r.getClickCount() == 2) {
                    Mon m = row.getItem();
                    this.txtmaMon.setText(m.getMaMon());
                    this.txtTenMon.setText(m.getTen());
                    this.txtThoiDiem.setText(m.getThoiDiemBan());
                    this.txtGiaBan.setText(String.valueOf(m.getGiaBan()));
                    this.cbTinhtrang.setValue(m.isTinhTrangCon());
                    this.cbLoai.setValue(m.getLoai());
                }
            });
            return row;

        });

    }

    private void reLoad(String kw) {
        this.tbMon.getItems().clear();
        this.tbMon.setItems(Ultils.getListMon(kw));
    }

    private void clear() {
        this.txtmaMon.clear();
        this.txtTenMon.clear();
        this.txtGiaBan.clear();
        this.txtThoiDiem.clear();

    }

    @FXML
    private void deleteMonAction(ActionEvent event) {
        this.tbMon.getSelectionModel().getSelectedItem();
        if (txtmaMon.getText().isEmpty()) {
            Ultils.getAlert("Thông tin món trống !!!", Alert.AlertType.WARNING);
        } else {
               try {
                
                btDel.setOnMouseClicked(et -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Bạn chắc chưa?");
                    alert.showAndWait().ifPresent(bt -> {
                        if (bt == ButtonType.OK) {

                            this.deleteMon(txtmaMon.getText(), txtTenMon.getText(),
                                    Double.parseDouble(txtGiaBan.getText()),
                                    cbTinhtrang.getSelectionModel().getSelectedItem().toString(),
                                    txtThoiDiem.getText(),
                                    cbLoai.getSelectionModel().getSelectedItem());
                            this.clear();
                            this.reLoad("");

                        }
                    });
                });

        
            } catch (Exception ex) {
                Logger.getLogger(QuanLiBanController.class.getName()).log(Level.SEVERE, null, ex);
//                Ultils.getAlert("", Alert.AlertType.NONE)
            }
            
        

        }
    }

    @FXML
    private void upDateAction(ActionEvent event
    ) {
        this.tbMon.getSelectionModel().getSelectedItem();

        if (txtmaMon.getText().isEmpty() || txtTenMon.getText().isEmpty()
                || txtGiaBan.getText().isEmpty() || txtThoiDiem.getText().isEmpty()
                || cbTinhtrang.getSelectionModel().getSelectedItem().toString().isEmpty()
                || cbLoai.getSelectionModel().getSelectedItem() == null) {
            Ultils.getAlert("Thông tin món trống !!!", Alert.AlertType.WARNING);

        } else if (Double.parseDouble(txtGiaBan.getText()) <= 0.0) {
            Ultils.getAlert("Giá bán không hợp lệ !!!", Alert.AlertType.WARNING);

        } else {
            if (this.upDateMon(txtmaMon.getText(), txtTenMon.getText(),
                    Double.parseDouble(txtGiaBan.getText()),
                    cbTinhtrang.getSelectionModel().getSelectedItem().toString(),
                    txtThoiDiem.getText(),
                    cbLoai.getSelectionModel().getSelectedItem())) {
                Ultils.getAlert("Thành công !!!", Alert.AlertType.INFORMATION);
                this.clear();
                this.reLoad("");

            } else {
                Ultils.getAlert("Thất bại !!!", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void addMonAction(ActionEvent event
    ) {
        String m = UUID.randomUUID().toString();
        if (txtTenMon.getText().isEmpty()
                || txtGiaBan.getText().isEmpty() || txtThoiDiem.getText().isEmpty()
                || cbTinhtrang.getSelectionModel().getSelectedItem().toString().isEmpty()
                || cbLoai.getSelectionModel().getSelectedItem().toString().isEmpty()) {
            Ultils.getAlert("Thông tin món trống !!!", Alert.AlertType.WARNING);

        } else {
            if (Double.parseDouble(txtGiaBan.getText()) <= 0.0) {
                Ultils.getAlert("Giá bán không hợp lệ !!!", Alert.AlertType.WARNING);

            } else {
                if (this.addMon(m, txtTenMon.getText(),
                        Double.parseDouble(txtGiaBan.getText()),
                        cbTinhtrang.getSelectionModel().getSelectedItem().toString(),
                        txtThoiDiem.getText(),
                        cbLoai.getSelectionModel().getSelectedItem())) {
                    Ultils.getAlert("Thành công !!!", Alert.AlertType.INFORMATION);
                    this.clear();
                    this.reLoad("");

                } else {
                    Ultils.getAlert("Thất Bại !!!", Alert.AlertType.ERROR);
                }
            }
        }
    }

    @FXML
    private void troVeTrangChinh(ActionEvent event
    ) {
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
    private void denQLBan(ActionEvent event
    ) {
        try {
            Parent login = FXMLLoader.load(getClass().getResource("QuanLiBan.fxml"));
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

    public boolean addMon(String m, String ten, double gb, String tt, String tdb,
            DanhMuc l) {
        boolean rs = false;
        Mon mon = new Mon(m, ten, gb, tt, tdb, l);
        try {
            if (!ten.isEmpty() && gb > 0.0 && !tt.isEmpty() && !tdb.isEmpty()) {

                Ultils.addOrUpdateMon(mon);
                rs = true;
            }

        } catch (ExceptionInInitializerError ex) {
            System.err.println(ex.getMessage());
            rs = false;

        }
        return rs;

    }

    public boolean upDateMon(String ma, String ten, double gb, String tt, String tdb,
            DanhMuc l) {
        boolean rs = false;
        //Lấy bàn, trả về Ban
        if (!ma.isEmpty() && !ten.isEmpty() && !tt.isEmpty() && !tdb.isEmpty()
                && (gb > 0.0)) {
            try {
                Mon m = new Mon();

                m.setMaMon(ma);
                m.setTen(ten);
                m.setGiaBan(gb);
                m.setTinhTrangCon(tt);
                m.setThoiDiemBan(tdb);
                m.setLoai(l);

                Ultils.addOrUpdateMon(m);
                rs = true;
            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            rs = false;
        }

        return rs;
    }

    public boolean deleteMon(String ma, String ten, double gb, String tt, String tdb,
            DanhMuc l) {
        boolean rs = false;
        Mon m = new Mon(ma, ten, gb, tt, tdb, l);

        if (ma.isEmpty()) {

            rs = false;
        } else {
            Ultils.deleteMons(m);
            rs = true;

        }

        return rs;
    }

}
