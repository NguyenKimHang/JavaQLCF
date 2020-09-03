/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import static quanliquancaphe.Ultils.factory;
import quanliquancaphe.hibernate.HibernateUtils;
import quanliquancaphe.models.NhanVien;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class DangNhapController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtTenTaiKhoan;
    @FXML
    private PasswordField txtMatKhau;
    @FXML
    private Button btDangNhap;

    @FXML
    private Button btThoat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void fXDangNhap(ActionEvent event) {
        if (!txtTenTaiKhoan.getText().isEmpty() && !txtMatKhau.getText().isEmpty()) {

            if (getLogin(txtTenTaiKhoan.getText(), txtMatKhau.getText())) {
                try {
                    Parent login = FXMLLoader.load(getClass().getResource("GiaoDienChinh.fxml"));
                    Scene loginScene = new Scene(login);

                    Stage window = (Stage) ((Node) event.getSource()).getScene()
                            .getWindow();
                    window.setScene(loginScene);
                    window.show();
                } catch (IOException ex) {
                    Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Ultils.getAlert("Tài khoản không tồn tại!", Alert.AlertType.ERROR);
            }

        } else {
            if (txtTenTaiKhoan.getText().isEmpty() && !txtMatKhau.getText().isEmpty()) {
                Ultils.getAlert("Tài khoản không được để trống!",
                        Alert.AlertType.ERROR);
            } else if (txtTenTaiKhoan.getText().isEmpty() && txtMatKhau.getText().isEmpty()) {
                Ultils.getAlert("Tài Khoản và Mật Khẩu không được để trống !!",
                        Alert.AlertType.ERROR);
            } else if (!txtTenTaiKhoan.getText().isEmpty() && txtMatKhau.getText().isEmpty()) {
                Ultils.getAlert("Mật Khẩu không được để trống!",
                        Alert.AlertType.ERROR);
            } else if (txtTenTaiKhoan.getText().length() > 25 || txtMatKhau.getText().length() > 25) {
                Ultils.getAlert("Tài khoản và mật khẩu tối đa 25 ký tự!",
                        Alert.AlertType.ERROR);
            }
        }
    }

    public void btThoatHandler(ActionEvent event) throws IOException {
        btThoat.setOnMouseClicked(et -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Bạn chắc chưa?");
            alert.showAndWait().ifPresent(bt -> {
                if (bt == ButtonType.OK) {
                    Stage stage = (Stage) btThoat.getScene().getWindow();
                    stage.close();
                }

            });
        });

    }

    public boolean getLogin(String u, String p) {
        boolean kq = false;
        Session session = factory.openSession();
        String hql = "FROM NhanVien WHERE tenTaiKhoan = :tenTaiKhoan and "
                + "matKhau = :matKhau";
        Query q = session.createQuery(hql);
        q.setParameter("tenTaiKhoan", u);
        q.setParameter("matKhau", p);
//        Criteria cr = session.createCriteria(NhanVien.class);
//        Transaction r = session.beginTransaction();
//        Query q = session.createQuery("from NhanVien");
//        cr.add(Restrictions.eq("tenTaiKhoan", String.format("%%%s%%", u)));
//        cr.add(Restrictions.eq("matKhau", String.format("%%%s%%", p)));
        List<NhanVien> rs = q.list();
        //r.commit();
        session.close();
        if (rs.size() == 1) {
            kq = true;
        }
        return kq;
    }
}
