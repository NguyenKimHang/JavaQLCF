package quanliquancaphe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ThongKeController implements Initializable {

    @FXML
    private TextField txtNhap;
    @FXML
    private Button btTheoThang;
    @FXML
    private Button btTheoNam;
    @FXML
    private Button btTrangChu;
    @FXML
    StackPane bd = new StackPane();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void thongkeThangHandler(ActionEvent e) throws ParseException {
        this.bd.getChildren().clear();
        String nam = txtNhap.getText();
        if (!Ultils.checkNam(nam)) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Cảnh báo ");
            a.setContentText(" Bạn cần nhập năm hợp lệ. VD: 2019 ");
            a.show();
        } else {
            int n = Integer.parseInt(nam);
            List<Double> ls = Ultils.listDoanhThuT(n);

            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Tháng");
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Doanh thu");
            BarChart chart = new BarChart(xAxis, yAxis);
            for (int i = 1; i <= 12; i++) {
                XYChart.Series s = new XYChart.Series();
                String thang = String.format("%s", i);
                s.setName(thang);
                s.getData().add(new XYChart.Data(thang, ls.get(i - 1)));
                chart.getData().add(s);
            }
            bd.getChildren().add(chart);
        }

    }

    public void thongkeQuyHandler(ActionEvent e) throws ParseException {
        this.bd.getChildren().clear();
        String nam = txtNhap.getText();
        if (!Ultils.checkNam(nam)) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Cảnh báo ");
            a.setContentText(" Bạn cần nhập năm hợp lệ. VD: 2019 ");
            a.show();
        } else {
            int n = Integer.parseInt(nam);
            List<Double> ls = Ultils.listDoanhThuT(n);
            ArrayList<Double> lsK = new ArrayList<>(4);

            for (int i = 0; i <= 3; i++) {
                double tien = 0;
                for (int t = 0; t < 3; t++) {
                    tien += ls.get(i * 3 + t);
                }
                lsK.add(i, tien);
            }
            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Quý");
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Doanh thu");
            BarChart chart = new BarChart(xAxis, yAxis);
            for (int i = 1; i <= 4; i++) {
                XYChart.Series s = new XYChart.Series();
                String thang = String.format("%s", i);
                s.setName(thang);
                s.getData().add(new XYChart.Data(thang, lsK.get(i - 1)));
                chart.getData().add(s);
            }

            bd.getChildren().add(chart);
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

}
