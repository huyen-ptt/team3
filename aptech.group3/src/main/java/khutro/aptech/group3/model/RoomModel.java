/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khutro.aptech.group3.model;

import java.sql.Timestamp;

/**
 *
 * @author CLD
 */
public class RoomModel {

    private int id;
    private String roomName;
    private String roomDescription;
    private Double roomPrice;
    private int roomOccupancy;
    private boolean roomStatus;
    private String roomType;
    private Double roomArea;
    private Timestamp createdAt; // Add this field
    private String roomImage;

    public RoomModel(String roomImage, String roomName, Double roomPrice, int id, int roomOccupancy, Boolean status, String roomDescription, Double roomArea, Timestamp createdAt1) {
        this.id = id;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.roomPrice = roomPrice;
        this.roomOccupancy = roomOccupancy;
        this.roomStatus = roomStatus;
        this.roomType = roomType;
        this.roomArea = roomArea;
        this.roomImage = roomImage;
    }

    public RoomModel(String roomImage, String roomName, Double roomPrice, int id, Boolean status, String roomDescription, Double roomArea) {
        this.id = id;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.roomPrice = roomPrice;
        this.roomOccupancy = roomOccupancy;
        this.roomStatus = roomStatus;
        this.roomType = roomType;
        this.roomArea = roomArea;
        this.createdAt = createdAt;
        this.roomImage = roomImage;
    }

    public RoomModel(int i, String no_rooms_found) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getRoomOccupancy() {
        return roomOccupancy;
    }

    public void setRoomOccupancy(int roomOccupancy) {
        this.roomOccupancy = roomOccupancy;
    }

    public boolean getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(boolean roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Double getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(Double roomArea) {
        this.roomArea = roomArea;
    }
    
//    public void getroomImage(String roomImage){
//        this.roomImage = roomImage;
//    }
//    
//    public void setroomImage(String roomImage) {
//        this.roomImage = roomImage;
//    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "RoomModel{" + "id=" + id + ", roomName=" + roomName + ", roomDescription=" + roomDescription + ", roomPrice=" + roomPrice + ", roomOccupancy=" + roomOccupancy + ", roomStatus=" + roomStatus + ", roomType=" + roomType + ", createdAt=" + createdAt + '}';
    }

<<<<<<< HEAD
    

    

=======
    public String getroomImage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
>>>>>>> 31dbef31333c37b0f01dec24b56d41a54a2d5527
}