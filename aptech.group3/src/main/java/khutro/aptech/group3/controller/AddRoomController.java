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
    }

    public void handleSave() {
        String name = nameTextField.getText().trim();
        String price = priceTextField.getText().trim();
        String occupancy = occupancyTextField.getText().trim();
        String status = statusTextField.getText().trim();
        String area = areaTextField.getText().trim();
        String type = typeTextField.getText().trim();
        String description = descriptionTextArea.getText().trim();


        // Tạo đối tượng RoomModel từ các giá trị đã hứng
        RoomModel roomModel = new RoomModel(name, description, Double.valueOf(price), Integer.parseInt(occupancy), Boolean.parseBoolean(status), type, Double.valueOf(area));

        boolean isSuccess = roomDao.insertRoom(roomModel);
        if (isSuccess) {
            clearFields();
            refreshTable();
            System.out.println("Thành công");
        } else {
            System.out.println("Thất bại");
        }
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

}
