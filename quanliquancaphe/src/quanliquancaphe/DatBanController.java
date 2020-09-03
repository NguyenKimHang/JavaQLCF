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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import quanliquancaphe.hibernate.HibernateUtils;
import quanliquancaphe.models.Ban;
import quanliquancaphe.models.ChiTietDonHang;
import quanliquancaphe.models.HoaDon;
import quanliquancaphe.models.DanhMuc;
import quanliquancaphe.models.Mon;
import quanliquancaphe.models.NhanVien;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class DatBanController implements Initializable {

    private final double WIDTH = 120;
    private final double HEIGHT = 60;
    //private final int SIZE = Ultils.countBan();

    @FXML
    private FlowPane fpBan;
    @FXML
    private TableColumn colLoai;
    @FXML
    private TextField txtSoLuong;
    @FXML
    private TableView<Mon> tbMon;
    @FXML
    private TableColumn colTenMontheoLoai;
    @FXML
    private TableColumn colGiaBan;
    @FXML
    private TableView<ChiTietDonHang> tbHoaDon;
    @FXML
    private TableColumn colTenMon;
    @FXML
    private TableColumn colDonGia;
    @FXML
    private TableColumn colSoluong;
    @FXML
    private TableColumn colThanhTien;

    @FXML
    private TableView<Ban> tbBan;
    @FXML
    private TableColumn colTenBan;
    @FXML
    private TableColumn colSucChua;
    @FXML
    private TableColumn colTinhtrang;
    @FXML
    private TableColumn colViTri;
    @FXML
    private Label lbTongTien;
    @FXML
    private TextField txtTienKHachHang;
    @FXML
    private Label lbTienThua;
    @FXML
    private Button btThoat;
    @FXML
    private TextField txtNhapTimKiem;
    double tongTien;
    @FXML
    private DatePicker dateNgayDat;
    @FXML
    private Button btDel;
    @FXML
    private Button btThem;

    //private Button btBan;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //      this.loadBan();
//        SpinnerValueFactory<Integer> svSoLuong
//                = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 11, 1);
        this.tbMon.setDisable(true);
        this.tbHoaDon.setVisible(false);
        this.btThem.setDisable(true);
        this.btDel.setDisable(true);
        this.txtSoLuong.setDisable(true);
        this.dateNgayDat.setDisable(true);

        //this.cbLoai.getItems().addAll(Ultils.getDanhMuc());
        this.colTenMontheoLoai.setCellValueFactory(new PropertyValueFactory("ten"));
        this.colGiaBan.setCellValueFactory(new PropertyValueFactory("giaBan"));
        this.colLoai.setCellValueFactory(new PropertyValueFactory("loai"));

        this.colTenMon.setCellValueFactory(new PropertyValueFactory("tenMon"));
        this.colSoluong.setCellValueFactory(new PropertyValueFactory("soLuong"));
        this.colDonGia.setCellValueFactory(new PropertyValueFactory("donGia"));
        this.colThanhTien.setCellValueFactory(new PropertyValueFactory("thanhTien"));

        this.colTenBan.setCellValueFactory(new PropertyValueFactory("tenBan"));
        this.colSucChua.setCellValueFactory(new PropertyValueFactory("sucChua"));
        this.colTinhtrang.setCellValueFactory(new PropertyValueFactory("tinhTrangTrong"));
        this.colViTri.setCellValueFactory(new PropertyValueFactory("viTriBan"));

        this.reLoadMon("");
        this.reloadB("");
        this.reLoadCTHD("");

        //Tìm kiếm
        this.txtNhapTimKiem.textProperty().addListener(et -> {
            this.reloadB(this.txtNhapTimKiem.getText());
        });

        Ban b = tbBan.getSelectionModel().getSelectedItem();

        //Sự kiện click trên dòng
        this.tbBan.setRowFactory(tbb -> {
            TableRow<Ban> row = new TableRow<>();
            row.setOnMouseClicked(r -> {
                // Đúp click
                if (r.getClickCount() == 2) {
                    this.tbHoaDon.setVisible(true);
                    //  this.layCTDHTheoBan(b.getMaBan());

                    //}
//                    Ban b = row.getItem();
////                    this.txtMaBan.setText(b.getMaBan());
////                    this.txtTenBan.setText(b.getTenBan());
////                    this.txtSucChua.setText(String.valueOf(b.getSucChua()));
////                    this.cbTinhTrang.setValue(b.getTinhTrangTrong());
////                    this.txtViTriBan.setText(b.getViTriBan());
//                    System.out.println(Ultils.b.setMaBan(b.getMaBan()));
                    this.tbMon.setDisable(false);

                    this.btThem.setDisable(false);
                    this.btDel.setDisable(false);
                    this.txtSoLuong.setDisable(false);
                    this.dateNgayDat.setDisable(false);

                }
            });
            return row;

        });
    }

//    private void layCTDHTheoBan(String b) {
//        //Ultils.b = tbBan.getSelectionModel().getSelectedItem();
//        this.tbHoaDon.getItems().clear();
//        for (HoaDon h : Ultils.getHoaDonByMaBan(b)) {
//
//            tbHoaDon.setItems((ObservableList<ChiTietDonHang>) Ultils.getCTDHByHD(h));
//        }
//    }
    @FXML
    private void themMonVaoHandler(ActionEvent event) throws ParseException {
        Ultils.m = tbMon.getSelectionModel().getSelectedItem();
        Ultils.b = tbBan.getSelectionModel().getSelectedItem();
        //List<Mon> ds = Ultils.checkMon(Ultils.m);
//        String t = tt.setTinhTrangCon(Ultils.m.isTinhTrangCon());
//        if ("Đã đặt".equals(Ultils.b.getTinhTrangTrong()) || !Ultils.laydsHD(Ultils.id).isEmpty()) {
//            btThem.setOnMouseClicked(e -> {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setContentText("Bạn muốn thêm món?");
//                alert.showAndWait().ifPresent(bt -> {
//                    if (bt == ButtonType.OK) {
//                        ChiTietDonHang ctdh = new ChiTietDonHang();
//                        //ctdh.setIdHD(Ultils.hd.getMaHD());
//                        ctdh.setTenMon(Ultils.m.getTen());
//                        ctdh.setDonGia(Ultils.m.getGiaBan());
//                        ctdh.setSoLuong(Integer.parseInt(this.txtSoLuong.getText()));
//                        ctdh.setIdHD(Ultils.laydsHD(Ultils.id).get(0));
//                        ctdh.setIdMon(Ultils.m);
//                        Ultils.addOrUpdateCTDH(ctdh);
//                        this.reLoadCTHD("");
//
//                    }
//                });
//            });

        if (txtSoLuong.getText().isEmpty()) {
            Ultils.getAlert("Số lượng đang trống !!!", Alert.AlertType.WARNING);
        } else if (!Ultils.checknumber(txtSoLuong.getText())) {
            Ultils.getAlert("Nhập sai số lượng, mời nhập lại!", Alert.AlertType.WARNING);
        } else if (Integer.parseInt(this.txtSoLuong.getText()) <= 0) {
            Ultils.getAlert("Số lượng không được phép âm !!", Alert.AlertType.WARNING);
        } else if ("Hết".equals(Ultils.m.isTinhTrangCon())) {
            Ultils.getAlert("Món bạn chọn đã hết hàng", Alert.AlertType.WARNING);
        } else if (dateNgayDat.getValue() == null) {
            Ultils.getAlert("Chưa chọn ngày đặt!", Alert.AlertType.WARNING);
        } else {

            Date ngStart = Date.from(dateNgayDat.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

            Session session = Ultils.factory.openSession();
            Transaction trans = null;
            try {
                trans = session.beginTransaction();
                if ("Đã đặt".equals(Ultils.b.getTinhTrangTrong())) {
                    btThem.setOnMouseClicked(e -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("Bạn muốn thêm món?");
                        alert.showAndWait().ifPresent(bt -> {
                            if (bt == ButtonType.OK) {
//                   
                                // tongTien += Ultils.m.getGiaBan() * Integer.parseInt(txtSoLuong.getText());
//                                    Ultils.hd = Ultils.laydsHD(Ultils.id).get(0);
//
//                                    Ultils.hd.setTongTien(tongTien);
//                                    Ultils.addOrUpdateHoaDon(Ultils.hd);
                                // this.reLoadCTHD("");
                                Ultils.cthd = new ChiTietDonHang(Ultils.m.getTen(), Integer.parseInt(txtSoLuong.getText()),
                                        Ultils.m.getGiaBan(), Ultils.m, Ultils.hd,
                                        Ultils.m.getGiaBan() * Integer.parseInt(txtSoLuong.getText()));

                                session.save(Ultils.cthd);
                            }
                        });
                    });

                } else {
                    if (Ultils.laydsHD(Ultils.id).isEmpty()) {

                        Ultils.b.setTinhTrangTrong("Đã đặt");
                        Ultils.addOrUpdateBan(Ultils.b);

                        Date now = new Date();
                        tongTien += Ultils.m.getGiaBan() * Integer.parseInt(txtSoLuong.getText());
                        Ultils.id = UUID.randomUUID().toString();

                        Ultils.hd = new HoaDon(Ultils.id, Ultils.b, ngStart, now,
                                tongTien);
                        session.save(Ultils.hd);
                        Ultils.cthd = new ChiTietDonHang(Ultils.m.getTen(), Integer.parseInt(txtSoLuong.getText()),
                                Ultils.m.getGiaBan(), Ultils.m, Ultils.hd,
                                Ultils.m.getGiaBan() * Integer.parseInt(txtSoLuong.getText()));

                        session.save(Ultils.cthd);

                    }
                }

//                Ultils.cthd = new ChiTietDonHang(Ultils.m.getTen(), Integer.parseInt(txtSoLuong.getText()),
//                        Ultils.m.getGiaBan(), Ultils.m, Ultils.hd,
//                        Ultils.m.getGiaBan() * Integer.parseInt(txtSoLuong.getText()));
//
//                session.save(Ultils.cthd);
                trans.commit();

                this.reLoadCTHD("");
                Ultils.getAlert("Thành công", Alert.AlertType.CONFIRMATION);

            } catch (Exception e) {
                if (trans != null) {
                    trans.rollback();
                    Ultils.getAlert("Thất bại", Alert.AlertType.CONFIRMATION);
                }
            } finally {
                session.close();
            }

        }
    }

    @FXML
    private void XoaCTDH(ActionEvent event) {

        Ultils.cthd = this.tbHoaDon.getSelectionModel().getSelectedItem();
        try {
            btDel.setOnMouseClicked(et -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Bạn chắc chưa?");
                alert.showAndWait().ifPresent(bt -> {
                    if (bt == ButtonType.OK) {

                        Ultils.deleteCTDH(Ultils.cthd);
                        this.reLoadCTHD("");
                    }

                });

            });

        } catch (Exception ex) {
            Logger.getLogger(QuanLiBanController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void reLoadMon(String kw) {
        this.tbMon.getItems().clear();
        this.tbMon.setItems(Ultils.getListMon(kw));

    }

    private void reLoadCTHD(String kw) {
        this.tbHoaDon.getItems().clear();
        this.tbHoaDon.setItems(Ultils.getListCTHD(kw));
    }

    private void reloadB(String kw) {
        this.tbBan.getItems().clear();
        this.tbBan.setItems(getListBan(kw));
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

    private ObservableList getListBan(String kw) {
        Session sessions = HibernateUtils.getSessionFactory().openSession();
        Criteria cr = sessions.createCriteria(Ban.class);
        cr.addOrder(Order.asc("tenBan"));
        if (kw != null && !kw.equals("")) {
            kw = String.format("%%%s%%", kw);

            cr.add(Restrictions.or(Restrictions.ilike("viTriBan", kw),
                    Restrictions.ilike("tinhTrangTrong", kw)));
        }
        List<Ban> bans = cr.list();
        sessions.close();

        return FXCollections.observableArrayList(bans);

    }

//       private void loadBan() {
//        for (Ban b : Ultils.getBan()) {
//            Button btBan = new Button();
//
//            btBan.setPrefSize(WIDTH, HEIGHT);
//            String t = b.getTenBan();
//            btBan.setText(t);
//            btBan.setTextFill(Color.WHITE);
//            DropShadow shadow = new DropShadow();
//
//            btBan.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent e) {
//                    btBan.setEffect(shadow);
//                    // btBan.setStyle("-fx-background-color: lightblue");
//
//                }
//            });
//
//            btBan.setOnMouseClicked((MouseEvent et) -> {
//                this.tbMon.setDisable(false);
//                this.tbHoaDon.setVisible(true);
//                h.setMaHD(Ultils.getMaHoaDon(ma.toString()));
//                mhd = h.getMaHD();
//                b.setMaBan(Ultils.getMaBan(btBan.getText()));
//                ma = b.getMaBan();
//
//                System.out.println(ma);
//                b.setTenBan(Ultils.getTenBan(btBan.getText()));
//                tenBan = b.getTenBan();
//                b.setTenBan(Ultils.getTTBan(btBan.getText()));
//                ttt = b.getTinhTrangTrong();
//
//                List<ChiTietDonHang> dsCtdh;
//                dsCtdh = Ultils.getCTDHByHD((HoaDon) Ultils.getHoaDonByMaBan(ma));
//                for (ChiTietDonHang ctdh : dsCtdh) {
//                    tbHoaDon.getSelectionModel().getSelectedItem();
//                    System.out.println(ctdh.getIdHD());
//                    this.reLoadCTHD("");
//                }
//                btBan.setStyle("-fx-background-color: orange");
//                btBan2 = btBan;
//                //HoaDon h;
//                String id = UUID.randomUUID().toString();
//                Date now = new Date();
//                h = new HoaDon(id, b, now, now, 50004);
//                //h.setMaBan(b);
//                //System.out.println(h.getMaBan().toString());
//                Ultils.addOrUpdateHoaDon(h);
//                this.reLoadCTHD("");
////                for (ChiTietDonHang h : Ultils.laydsCTDH()){
////                    h.getTenMon();
////                }
    //           });
//            btBan.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent e) {
//                    btBan.setEffect(null);
//                    switch (b.getTinhTrangTrong()) {
//                        case "Còn trống":
//                            btBan.setStyle("-fx-background-color: black");
//                            break;
//                        default:
//                            btBan.setStyle("-fx-background-color: pink");
//                            break;
//
//                    }
//                }
//            });
//
//            if ("Còn trống".equals(b.getTinhTrangTrong())) {
//                btBan.setStyle("-fx-background-color: black");
//
//            } else {
//                btBan.setStyle("-fx-background-color: pink");
//
//            }
//            switch (b.isTinhTrangTrong()) {
//                case "Còn trống":
//                    btBan.setStyle("-fx-background-color: black");
//                    break;
//                default:
//                    btBan.setStyle("-fx-background-color: pink");
//                    break;
//
//            }
//            fpBan.getChildren().add(btBan);
//
//        }
//
//    }
//        b.setMaBan(ma);
//        b.setTinhTrangTrong(ttt);
//        b.setTenBan(tenBan);
//        if ("Đã đặt".equals(b.getTinhTrangTrong())) {
//
//        } else {
//            ChiTietDonHang ctdh = new ChiTietDonHang(m.getTen(), Integer.parseInt(txtSoLuong.getText()),
//                    m.getGiaBan(), m, h, m.getGiaBan() * Double.parseDouble(txtSoLuong.getText()));
//            Ultils.addOrUpdateCTDH(ctdh);
//            System.out.println(h.getMaHD());
//            System.out.println(h.getMaBan().toString());
//            Ultils.getAlert("Thành công", Alert.AlertType.CONFIRMATION);
    // b.setTinhTrangTrong("Đã đặt");
//            btBan2.setStyle("-fx-background-color: pink");
    // Ultils.addOrUpdateBan(b);
    //this.tbMon.setDisable(true);
    //this.reLoadCTHD("");
//        }
    //       ChiTietDonHang ctdh = new ChiTietDonHang();
////        String stringDate = "2020-1-1";
////        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
}
