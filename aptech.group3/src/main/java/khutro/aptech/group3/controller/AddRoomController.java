/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khutro.aptech.group3.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import khutro.aptech.group3.database.ConnectionProvider;
import khutro.aptech.group3.model.RoomModel;
import khutro.aptech.group3.dao.RoomDaoImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 *
 * @author CLD
 */
public class AddRoomController implements Initializable {

    ConnectionProvider connection = new ConnectionProvider();
    RoomDaoImpl roomDao = new RoomDaoImpl(connection.getConnection());

    @FXML
    private TextField nameTextField, priceTextField, occupancyTextField, statusTextField, areaTextField, typeTextField;

    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TableView<RoomModel> tableViewRoom;

    @FXML
    private TableColumn<RoomModel, String> nameColumn;

    @FXML
    private TableColumn<RoomModel, Integer> roomIdColumn;

    @FXML
    private TableColumn<RoomModel, String> roomDescriptionColumn;

    @FXML
    private TableColumn<RoomModel, Double> priceColumn;

    @FXML
    private TableColumn<RoomModel, Integer> maxOccupancyColumn;

    @FXML
    private TableColumn<RoomModel, Boolean> statusColumn;

    @FXML
    private TableColumn<RoomModel, Double> roomAreaColumn;
    @FXML
    private Button deleteButton;
    @FXML
    private TableColumn<RoomModel, String> typeColumn;
    private ObservableList<RoomModel> roomList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        roomDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("roomDescription"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("roomPrice"));
        maxOccupancyColumn.setCellValueFactory(new PropertyValueFactory<>("roomOccupancy"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));
        roomAreaColumn.setCellValueFactory(new PropertyValueFactory<>("roomArea"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));

        refreshTable();

        tableViewRoom.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Check for a single mouse click
                // Get the selected item from the TableView
                RoomModel selectedItem = tableViewRoom.getSelectionModel().getSelectedItem();

                if (selectedItem != null) {
                    // Now you can access the data from the selected item
                    int id = selectedItem.getId();
                    String roomName = selectedItem.getRoomName();
                    // Access other properties as needed
                    System.out.println("Selected Item: " + id + ", " + roomName);
                }
            }
        });

        ContextMenu contextMenu = new ContextMenu();
        MenuItem editMenuItem = new MenuItem("Edit");
        MenuItem deleteMenuItem = new MenuItem("Delete");
        contextMenu.getItems().addAll(editMenuItem, deleteMenuItem);

        tableViewRoom.setContextMenu(contextMenu);

    }

    public void refreshTable() {
        List<RoomModel> rooms = roomDao.getAllRooms(); // khai báo arraylist hứng dữ liệu 
        roomList = FXCollections.observableArrayList(rooms); // set dữ liệu trên vào roomList
        tableViewRoom.setItems(roomList); // 
        System.out.println(rooms);
    }

    public void handleSave() {
        String name = nameTextField.getText().trim();
        String price = priceTextField.getText().trim();
        String occupancy = occupancyTextField.getText().trim();
        String status = statusTextField.getText().trim();
        String area = areaTextField.getText().trim();
        String type = typeTextField.getText().trim();
        String description = descriptionTextArea.getText().trim();

        try {
            // Tạo đối tượng RoomModel từ các giá trị đã hứng
            RoomModel roomModel = new RoomModel(name, description, Double.valueOf(price), Integer.parseInt(occupancy), Boolean.parseBoolean(status), type, Double.valueOf(area));

            boolean isSuccess = roomDao.insertRoom(roomModel);
            if (isSuccess) {
                clearFields();
                refreshTable();
                showAlert("Success", "Room information saved successfully.");
            } else {
                showAlert("Error", "Failed to save room information.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid number format in one or more fields.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void clearFields() {
        nameTextField.clear();
        priceTextField.clear();
        occupancyTextField.clear();
        statusTextField.clear();
        areaTextField.clear();
        typeTextField.clear();
        descriptionTextArea.clear();
    }

    //UPDATE
    @FXML
    public void roomUpdate() {
        // Retrieve the selected item from the table
        RoomModel selectedItem = tableViewRoom.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            // Get the current values from the selected item
            String currentName = selectedItem.getRoomName();
            String currentPrice = String.valueOf(selectedItem.getRoomPrice());
            String currentOccupancy = String.valueOf(selectedItem.getRoomOccupancy());
            String currentStatus = String.valueOf(selectedItem.isRoomStatus());
            String currentArea = String.valueOf(selectedItem.getRoomArea());
            String currentType = selectedItem.getRoomType();
            String currentDescription = selectedItem.getRoomDescription();

            // Populate the input fields with the current values
            nameTextField.setText(currentName);
            priceTextField.setText(currentPrice);
            occupancyTextField.setText(currentOccupancy);
            statusTextField.setText(currentStatus);
            areaTextField.setText(currentArea);
            typeTextField.setText(currentType);
            descriptionTextArea.setText(currentDescription);

            // You can also update the UI or perform other actions as needed
        } else {
            showAlert("Error", "Please select a room to update.");
        }
    }

    @FXML
    public void deleteButtonAction() {
        String name = nameTextField.getText().trim();
        String price = priceTextField.getText().trim();
        String occupancy = occupancyTextField.getText().trim();
        String status = statusTextField.getText().trim();
        String area = areaTextField.getText().trim();
        String type = typeTextField.getText().trim();
        String description = descriptionTextArea.getText().trim();

        RoomModel roomModel = tableViewRoom.getSelectionModel().getSelectedItem();

        // Tạo đối tượng RoomModel từ các giá trị đã hứng
//        RoomModel roomModel = new RoomModel(name, description, Double.valueOf(price), Integer.parseInt(occupancy), Boolean.parseBoolean(status), type, Double.valueOf(area));
        boolean isSuccess = roomDao.deleteRoom(roomModel);
        if (isSuccess) {
            clearFields();
            refreshTable();
            System.out.println("Thành công");
        } else {
            System.out.println("Thất bại");
        }
    }
    // chức năng tìm kiếm
    @FXML
    private TextField searchTextField;
//    @FXML
//    private TableView<RoomModel> roomTableView;

    // Define your TableColumn variables...
    // Other methods...
    @FXML
    private void handleSearch() {
        String searchTerm = searchTextField.getText().trim();

        // Connect to the database
        try {
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/khutro", "username", "password");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/khutro", "root", "");
            // Prepare and execute the search query
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM room WHERE room_name LIKE ?");
            stmt.setString(1, "%" + searchTerm + "%");

            ResultSet rs = stmt.executeQuery();

            // Populate the TableView with the results
            ObservableList<RoomModel> roomList = FXCollections.observableArrayList();
            while (rs.next()) {
                // Create Room objects from the ResultSet and add them to roomList
                int id = rs.getInt("id");
                String roomName = rs.getString("room_name");
                String roomDescription = rs.getString("room_description");
                Double roomPrice = rs.getDouble("price");
                int roomOccupancy = rs.getInt("max_occupancy");
                boolean roomStatus = rs.getBoolean("status");
                String roomType = rs.getString("type");
                Double roomArea = rs.getDouble("room_area");
//            Timestamp createdAt = rs.getTimestamp("created_at");

                RoomModel room = new RoomModel(roomName, roomDescription, roomPrice, id, roomStatus, roomType, roomArea);
                roomList.add(room);
            }
            System.out.println("here");
            System.out.println(roomList.toString());
            // Set the TableView items
            tableViewRoom.setItems(roomList);
            tableViewRoom.refresh();
            // Close the connections
            rs.close();
            stmt.close();
            conn.close();

            // Handle case where no results are found
            if (roomList.isEmpty()) {
                // Room not found, display a message
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Room Not Found");
                alert.setHeaderText(null);
                alert.setContentText("The room with ID " + searchTerm + " does not exist.");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Handle the case where searchTerm is not a valid number
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid room ID.");
            alert.showAndWait();
        }
    }

}
