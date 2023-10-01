///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package khutro.aptech.group3.controller;
//
//import java.net.URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import khutro.aptech.group3.model.RoomModel;


public class DetailController {
    @FXML
    private Text textNameDetail;

    @FXML
    private Text textAreaDetail;
    
    @FXML
    private Text textTypeDetail;
    
    @FXML
    private Text textStatusDetail;
    
    @FXML
    private Text textPriceDetail;
    
    @FXML
    private Text textDesciptionDetail;
    
    @FXML
    private Text textOccupancyDetail;
private int roomId;

    public void setRoomId(int roomId) {
        this.roomId = roomId;
        // Sử dụng roomId để truy xuất và hiển thị thông tin chi tiết về phòng
        loadRoomDetails();
    }

    private void loadRoomDetails() {
        // Sử dụng roomId để lấy thông tin chi tiết từ cơ sở dữ liệu và hiển thị trong giao diện
        RoomModel roomModel = getRoomDetailsFromDatabase(roomId);
        setData(roomModel);
    }

    // lấy thông tin chi tiết phòng từ cơ sở dữ liệu
    
        
        
    
    private RoomModel getRoomDetailsFromDatabase(int roomId) {
    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/khutro", "root", "");
        // truy xuất thông tin chi tiết về phòng dựa trên roomId
        String query = "SELECT * FROM Room WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, roomId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String roomName = resultSet.getString("room_name");
            String roomDescription = resultSet.getString("room_description");
            Double price = resultSet.getDouble("price");
            int maxOccupancy = resultSet.getInt("max_occupancy");
            Boolean status = resultSet.getBoolean("status");
            String type = resultSet.getString("type");
            Double roomArea = resultSet.getDouble("room_area");
            Timestamp createdAt = resultSet.getTimestamp("created_time");
            // Trả về đối tượng RoomModel kèm theo thông tin chi tiết
            return new RoomModel(roomName, roomDescription, price, id, maxOccupancy, status, type, roomArea, createdAt);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null; // Return null if room details are not found
}

    private void setData(RoomModel roomModel) {
        textNameDetail.setText("Room name: "+roomModel.getRoomName());
        textDesciptionDetail.setText("Room description: "+roomModel.getRoomDescription());
        textPriceDetail.setText("Room price: "+roomModel.getRoomPrice());
        textAreaDetail.setText("Room area: "+roomModel.getRoomArea());
        textTypeDetail.setText("Room type: "+roomModel.getRoomType());
        textStatusDetail.setText("Room status: "+roomModel.getRoomStatus());
        textOccupancyDetail.setText("Room occupancy: "+roomModel.getRoomOccupancy());
    }
    
}

