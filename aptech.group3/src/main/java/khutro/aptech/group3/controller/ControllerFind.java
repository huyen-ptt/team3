/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khutro.aptech.group3.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import khutro.aptech.group3.dao.IRoomDao;
import khutro.aptech.group3.dao.RoomDaoImpl;
import khutro.aptech.group3.database.ConnectionProvider;
import khutro.aptech.group3.model.RoomModel;


/**
 *
 * @author VuongTheThuLinh
 */
public class ControllerFind {
    @FXML
    private TextField searchField;

    @FXML
    private ListView<RoomModel> roomListView;

    List<RoomModel> rooms;
    
    //truy xuất văn bản từ searchField, 
    //gọi searchRooms bằng SearchFind và sau đó hiển thị kết quả bằng displayResults.
    @FXML
    public void SearchFind(){
        String query = searchField.getText();
        List<RoomModel> results = searchRooms(query);
        displayResults(results);
    }
    
    //searchRooms lọc danh sách phòng dựa trên truy vấn tìm kiếm được cung cấp
    private List<RoomModel> searchRooms(String query) {
        return rooms.stream()
                   .filter(rooms -> rooms.getRoomName().contains(query))
                    .collect(Collectors.toList());
       
    }
    //cập nhật roomListView với kết quả thu được từ tìm kiếm
    private void displayResults(List<RoomModel> results) {
        ObservableList<RoomModel> observableList = FXCollections.observableArrayList(results);
        roomListView.setItems(observableList);
    }
    
    
//    private List<RoomModel> getRoomsFromDatabase() throws SQLException {
//        List<RoomModel> rooms = new ArrayList<>();
//
//        String url = "jdbc:mysql://localhost:3306/"; // Replace with your actual database URL
//        String DB_USER = "root";
//        String DB_PASSWORD = "";
//        String DATABASE_NAME = "khutro";
//        
//
//        try (Connection connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD)) {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM room");
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String roomName = resultSet.getString("room_name");
//                String roomDescription = resultSet.getString("room Description");
//                Double roomPrice = resultSet.getDouble("room price");
//                int roomOccupancy = resultSet.getInt("room Occupancy");
//                boolean roomStatus = resultSet.getBoolean("room Status");
//                String roomType = resultSet.getString("room type");
//                Double roomArea = resultSet.getDouble("room area");
//                Timestamp createdAt = resultSet.getTimestamp("room createdAt");
//                
//
//                RoomModel room = new RoomModel(id, roomName, roomDescription, roomPrice, roomOccupancy, roomStatus, roomType, roomArea, createdAt);
//                rooms.add(room);
//            }
//        }catch (SQLException e) {
//            e.printStackTrace(); // Handle the exception (e.g., log it, show an error message)
//        }
//
//        return rooms;
//    }
    
    
    private List<RoomModel> getRoomsFromDatabase() throws SQLException {
    try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/khutro", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM room");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String roomName = resultSet.getString("room_name");
                String roomDescription = resultSet.getString("room Description");
                Double roomPrice = resultSet.getDouble("room price");
                int roomOccupancy = resultSet.getInt("room Occupancy");
                boolean roomStatus = resultSet.getBoolean("room Status");
                String roomType = resultSet.getString("room type");
                Double roomArea = resultSet.getDouble("room area");
                Timestamp createdAt = resultSet.getTimestamp("room createdAt");
                

                RoomModel room = new RoomModel(id, roomName, roomDescription, roomPrice, roomOccupancy, roomStatus, roomType, roomArea, createdAt);
                rooms.add(room);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	return rooms;
}
//    @FXML
//    private ImageView roomImage;

    ConnectionProvider connection = new ConnectionProvider();
    RoomDaoImpl roomDao = new RoomDaoImpl(connection.getConnection());

    @FXML
    private TextField nameTextField, priceTextField, occupancyTextField, statusTextField, areaTextField;

    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TableView<RoomModel> tableViewRoom;
    @FXML
    private TableColumn<RoomModel, String> nameColumn;
    @FXML
    private TableColumn<RoomModel, String> roomDescriptionColumn;

    @FXML
    private TableColumn<RoomModel, Double> priceColumn;

    @FXML
    private TableColumn<RoomModel, Boolean> statusColumn;

    @FXML
    private TableColumn<RoomModel, Double> roomAreaColumn;

    
    private ObservableList<RoomModel> roomList;



    public void initialize() {
//        // Truy vấn cơ sở dữ liệu để lấy thông tin phòng trọ
////        RoomModel room = roomDao.getRoomInfo(); // Thay bằng phương thức truy vấn thích hợp
//
//        // Cập nhật các thành phần trong giao diện với dữ liệu từ cơ sở dữ liệu
////        roomImage.setImage(new Image(room.getImageUrl()));
//        roomAreaColumn.setText("Room area: " + room.getRoomArea());
//        priceColumn.setText("Price: " + room.getRoomPrice() + " VND");
//        roomDescriptionColumn.setText("Room description: " + room.getRoomDescription());
//        statusColumn.setText("Status: " + room.isRoomStatus());
//        nameColumn.setText("Room name: " + room.getRoomName());
    }
}

