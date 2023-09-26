/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khutro.aptech.group3.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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
    //    private Text textRoomName, textRoomDescription, textRoomPrice, textRoomArea, textRoomOccupancy;

    public void setData(RoomModel roomModel) {
        textRoomName.setText(roomModel.getRoomName());
        textRoomStatus.setText(""+roomModel.isRoomStatus());
        textRoomDescription.setText(roomModel.getRoomDescription());
        textRoomPrice.setText(""+roomModel.getRoomPrice());
        textRoomArea.setText(""+roomModel.getRoomArea());
    }

}
