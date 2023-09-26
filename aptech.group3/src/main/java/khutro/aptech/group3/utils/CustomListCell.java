/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khutro.aptech.group3.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import khutro.aptech.group3.controller.ItemRoomController;
import khutro.aptech.group3.model.RoomModel;

/**
 *
 * @author CLD
 */
public class CustomListCell extends ListCell<RoomModel> {
    public CustomListCell() {
        // Khởi tạo constructor nếu cần
    }
    
     // Ghi đè phương thức updateItem để hiển thị dữ liệu sách
    @Override
    protected void updateItem(RoomModel room, boolean empty) {
        super.updateItem(room, empty);

        if (empty || room == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Tạo và hiển thị giao diện từ item_book.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/khutro/aptech/group3/view/item_room.fxml"));
            try {
                Node node = loader.load();
                ItemRoomController controller = loader.getController();
                controller.setData(room); // Thiết lập dữ liệu sách vào giao diện
                setGraphic(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
