/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khutro.aptech.group3.controller;

import java.net.URL;
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

}