/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class GiaoDienChinhController implements Initializable {

    @FXML
    private Button btQLBan;
    @FXML
    private Button btQLMon;
    @FXML
    private Button btHoaDon;

    @FXML
    private Button btThoat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void vaoQLBan(ActionEvent event) {
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

    @FXML
    private void vaoQLMon(ActionEvent event) {
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

    @FXML
    private void vaoQLHoaDon(ActionEvent event) {
        try {
            Parent login = FXMLLoader.load(getClass().getResource("HoaDon.fxml"));
            Scene loginScene = new Scene(login);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void vaoDatBan(ActionEvent event) {
        try {
            Parent login = FXMLLoader.load(getClass().getResource("DatBan.fxml"));
            Scene loginScene = new Scene(login);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void vaoThongKe(ActionEvent event) {
        try {
            Parent login = FXMLLoader.load(getClass().getResource("ThongKe.fxml"));
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
