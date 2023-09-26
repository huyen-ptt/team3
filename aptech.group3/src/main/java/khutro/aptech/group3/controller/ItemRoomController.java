/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khutro.aptech.group3.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
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
        private Text textRoomName, textRoomDescription, textRoomPrice, textRoomArea;
//    private Image image;

    public void setData(RoomModel roomModel) {
//        imageViewThumb.setImage(image);
        textRoomName.setText("Room name: "+roomModel.getRoomName());
        textRoomDescription.setText("Room description: "+roomModel.getRoomDescription());
        textRoomPrice.setText("room price: "+roomModel.getRoomPrice());
        textRoomArea.setText("Room area: "+roomModel.getRoomArea());
    }

}
