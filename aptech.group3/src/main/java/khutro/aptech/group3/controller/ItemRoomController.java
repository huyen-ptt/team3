/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khutro.aptech.group3.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import khutro.aptech.group3.App;
import khutro.aptech.group3.model.RoomModel;

/**
 *
 * @author CLD
 */
public class ItemRoomController {

    @FXML
    private ImageView imageViewThumb; // Chưa xử lý
    
    @FXML
    private Text textRoomName, textRoomStatus, textRoomDescription, textRoomPrice, textRoomArea;

    public void setData(RoomModel roomModel) {

        textRoomName.setText("Room name: "+roomModel.getRoomName());
        textRoomDescription.setText("Room description: "+roomModel.getRoomDescription());
        textRoomPrice.setText("Room price: "+roomModel.getRoomPrice());
        textRoomArea.setText("Room area: "+roomModel.getRoomArea());
    
    }
    // khi cilck tên nhảy sang trang mới
    @FXML
    public void nextDetail() throws IOException {
//        App.setRoot("detail");
    // Lấy id phòng đã click
    String roomId = textRoomName.getText().replace("Room name: ", "");

    // Tải trang chi tiết FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/khutro/aptech/group3/view/detail.fxml"));
    Parent detailPage = loader.load();

    // Nhận bộ điều khiển cho trang chi tiết
    DetailController detailController = loader.getController();

    // Chuyển id phòng cho bộ điều khiển chi tiết
    detailController.setRoomId(Integer.parseInt(roomId));

    // Hiển thị trang chi tiết
    Stage stage = new Stage();
    stage.setScene(new Scene(detailPage));
    stage.show();
}
        
}

