/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khutro.aptech.group3.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import khutro.aptech.group3.App;
import khutro.aptech.group3.dao.RoomDaoImpl;
import khutro.aptech.group3.database.ConnectionProvider;
import khutro.aptech.group3.model.RoomModel;
import khutro.aptech.group3.utils.CustomListCell;

/**
 *
 * @author CLD
 */
public class DashboardController implements Initializable {

    ConnectionProvider connection = new ConnectionProvider();
    RoomDaoImpl roomDao = new RoomDaoImpl(connection.getConnection());
    @FXML
    private ListView<RoomModel> listViewRooms;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<RoomModel> rooms = roomDao.getAllRooms(); // khai báo arraylist hứng dữ liệu 
        System.out.println(rooms);

        // Gán danh sách dữ liệu cho ListView
        listViewRooms.getItems().addAll(rooms);

        // Gán giao diện từ item.fxml cho các mục trong ListView
        listViewRooms.setCellFactory(param -> new CustomListCell());

        // Sử dụng ChangeListener để theo dõi sự thay đổi trong việc chọn mục
        listViewRooms.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RoomModel>() {
            @Override
            public void changed(ObservableValue<? extends RoomModel> observable, RoomModel oldValue, RoomModel newValue) {
                if (newValue != null) {
                    // Lấy dữ liệu của mục đã chọn và thực hiện các xử lý cần thiết
                    String selectedRoomName = newValue.getRoomName();
                    Double selectedRoomPrice = newValue.getRoomPrice();

                    // In ra thông tin của mục đã chọn
                    System.out.println(newValue.toString());
                }
            }
        });

    }

    public void handelswitchDetail() throws IOException {
        App.setRoot("detail");
    }

}
