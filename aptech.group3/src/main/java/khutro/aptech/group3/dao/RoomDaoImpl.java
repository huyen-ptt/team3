/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khutro.aptech.group3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import khutro.aptech.group3.model.RoomModel;

/**
 *
 * @author CLD
 */
public class RoomDaoImpl implements IRoomDao {

    private Connection connection;

    public RoomDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<RoomModel> getAllRooms() {
        List<RoomModel> rooms = new ArrayList<>();
        String query = "SELECT * FROM Room";
        //try - with - resources
        try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String image = resultSet.getString("image");
                String room_name = resultSet.getString("room_name");
                String room_description = resultSet.getString("room_description");
                Double price = resultSet.getDouble("price");
                int max_occupancy = resultSet.getInt("max_occupancy");
                Boolean status = resultSet.getBoolean("status");
                Double room_area = resultSet.getDouble("room_area");
                String type = resultSet.getString("type");
                Timestamp created_time = resultSet.getTimestamp("created_time");

                RoomModel roomModel = new RoomModel(id, room_name, room_description, price, max_occupancy, status, type, room_area, created_time);
                rooms.add(roomModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public boolean insertRoom(RoomModel roomModel) {
        String query = "INSERT INTO Room (room_name, room_description, price, max_occupancy, status, room_area, type, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, roomModel.getRoomName());
            preparedStatement.setString(2, roomModel.getRoomDescription());
            preparedStatement.setDouble(3, roomModel.getRoomPrice());
            preparedStatement.setInt(4, roomModel.getRoomOccupancy());
            preparedStatement.setBoolean(5, roomModel.isRoomStatus());
            preparedStatement.setDouble(6, roomModel.getRoomArea());
            preparedStatement.setString(7, roomModel.getRoomType());
            preparedStatement.setString(8, roomModel.getImage()); 

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
